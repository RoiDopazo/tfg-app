import { Component, ViewChild, ElementRef } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  LatLng,
  CameraPosition,
  MarkerOptions,
  Marker,
  Polyline,
  HtmlInfoWindow
} from '@ionic-native/google-maps';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import moment from "moment";

declare var google;

/**
 * Generated class for the MapPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-map',
  templateUrl: 'map.html',
})

export class MapPage {
  private current_day_less;
  private current_day_plus;
  private current_day;
  private select_day;
  private mapRtd = false;

  private isEvent: boolean = false;
  private eventPlace;
  private rtdpoly = [];
  private index = 0;
  private route;
  private markerList = [];
  private infoWindowList = [];
  private coords = [];

  private markerEvent;
  private infWindowEvent;

  private mapReady: boolean = false;
  private map;
  directionsService = new google.maps.DirectionsService;
  directionsDisplay = new google.maps.DirectionsRenderer;

  constructor(public navCtrl: NavController, public navParams: NavParams, private googleMaps: GoogleMaps, private serviceManagerProvider: ServiceManagerProvider) {
    this.route = this.navParams.get("route");
    this.eventPlace = this.navParams.get("ePlace");
    let dataDays = this.navParams.get("dataDays");
    if (this.eventPlace != null) {
      this.select_day = this.navParams.get("day");
      this.isEvent = true;
    } else if (dataDays != null) {
      this.current_day_less = dataDays.current_day_less;
      this.current_day_plus = dataDays.current_day_plus;
      this.current_day = dataDays.current_day;
      this.select_day = dataDays.select_day;
    } else {
      this.initDayVariables();
    }
  }

  ionViewDidLoad() {
    this.initMap();

    let element: HTMLElement = document.getElementById("s_button" + (this.select_day));
    if (element != null) {
      element.classList.add("segment-activated", "activated");
    }
  }

  getCurrentDate() {
    moment.locale('es');
    let oneDayInMs = 86400000;
    return moment(this.route.startDate + oneDayInMs * (this.select_day - 1)).utc().format("DD [de] MMMM [de] YYYY");;
  }

  removeActive() {
    let element: HTMLElement = document.getElementById("s_button1");
    if (element != null) {
      element.classList.remove("segment-activated", "activated");
    }
  }

  initDayVariables() {
    this.current_day = 2;
    this.current_day_less = this.current_day - 1;
    this.current_day_plus = this.current_day + 1;
    this.select_day = this.current_day_less;
  }


  oneMoreDay() {
    let element: HTMLElement = document.getElementById("s_button" + (this.select_day - 1));
    if (!(this.route.numDays == this.current_day_plus)) {
      this.current_day_less = this.current_day_less + 1;
      this.current_day = this.current_day + 1;
      this.current_day_plus = this.current_day_plus + 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.add("segment-activated", "activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated", "activated");
      }
    }
    this.removeActive();
    this.reloadMapInfo();
  }

  oneDayLess() {
    let value = (parseInt(this.select_day) + 1);
    let element: HTMLElement = document.getElementById("s_button" + value);
    if (this.current_day_less > 1) {
      this.current_day_less = this.current_day_less - 1;
      this.current_day = this.current_day - 1;
      this.current_day_plus = this.current_day_plus - 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.add("segment-activated", "activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated", "activated");
      }
    }

    this.reloadMapInfo();
  }


  reloadMapInfo() {
    if (this.mapReady) {
      this.map.clear();
      this.showDayInMap(this.route.days[this.select_day - 1]);
      this.showRouteInMap(this.route.days[this.select_day - 1]);
    }
  }

  startNavigating() {

    let directionsService = new google.maps.DirectionsService;

    directionsService.route({
      origin: 'adelaide',
      destination: 'adelaide oval',
      travelMode: google.maps.TravelMode['DRIVING']
    }, (res, status) => {

      if (status == google.maps.DirectionsStatus.OK) {
        console.log(res);
      } else {
        console.warn(status);
      }
    });
  }


  initMap() {
    let element: HTMLElement = document.getElementById('mapMap');

    let style = [
      {
        "featureType": "administrative.land_parcel",
        "elementType": "labels",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      },
      {
        "featureType": "poi",
        "elementType": "labels.text",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      },
      {
        "featureType": "poi.business",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      },
      {
        "featureType": "road",
        "elementType": "labels.icon",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      },
      {
        "featureType": "road.local",
        "elementType": "labels",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      },
      {
        "featureType": "transit",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      }
    ]

    this.map = this.googleMaps.create(element, {styles: style});
    this.map.one(GoogleMapsEvent.MAP_READY).then(
      () => {
        this.mapReady = true;
        this.showDayInMap(this.route.days[this.select_day - 1]);
        this.showRouteInMap(this.route.days[this.select_day - 1]);
        if (this.isEvent) {
          this.showEventInMap(this.eventPlace);
        }
      }
    );
  }

  showEventInMap(eventPlace) {
    if (this.mapReady) {
      let infoWindow = new HtmlInfoWindow;
      let html = this.createEventInfoWindow(eventPlace);
      infoWindow.setContent(html);
      this.infWindowEvent = infoWindow;

      let m = this.map.addMarker({

        icon: 'blue',
        animation: 'DROP',
        position: {
          lat: eventPlace.lat,
          lng: eventPlace.lng
        }
      }).then(
        marker => {
          marker.on(GoogleMapsEvent.MARKER_CLICK).subscribe(
            () => {
              for (let inf of this.infoWindowList) {
                inf.close();
              }
              this.infWindowEvent.open(marker);
            }
          );
        }
        );
        this.map.moveCamera({
          target: {
            lat: eventPlace.lat,
            lng: eventPlace.lng
          },
          zoom: 18
        });
    }
  }

  showDayInMap(day) {
    this.map.clear();
    if (this.mapReady) {
      let pos = 0;
      let num = 0;
      for (let stay of day.stays) {
        let stayPlaceOrEvent = stay.place;
        if (stay.place == null) {
          stayPlaceOrEvent = stay.eventPlace;
        }
        let infoWindow = new HtmlInfoWindow;
        let html = this.createInfoWindow(stayPlaceOrEvent, stay);
        infoWindow.setContent(html);
        this.infoWindowList.push(infoWindow);
        this.coords.push({
          lat: stayPlaceOrEvent.lat,
          lng: stayPlaceOrEvent.lng
        });
        let m = this.map.addMarker({

          icon: 'red',
          animation: 'DROP',
          position: {
            lat: stayPlaceOrEvent.lat,
            lng: stayPlaceOrEvent.lng
          }
        }).then(
          marker => {
            this.markerList.push({ marker: marker, pos: num });
            if (num == day.stays.length - 1) {
              for (let m of this.markerList) {
                m.marker.on(GoogleMapsEvent.MARKER_CLICK).subscribe(
                  () => {
                    for (let inf of this.infoWindowList) {
                      inf.close();
                    }
                    this.infoWindowList[m.pos].open(m.marker);
                  }
                );
              }
            }
            num = num + 1;
          }
          );
        if (!(this.isEvent) && (pos == 0)) {
          this.map.moveCamera({
            target: {
              lat: stayPlaceOrEvent.lat,
              lng: stayPlaceOrEvent.lng
            },
            zoom: 18
          });
        }
        pos = pos + 1;
      }
    }
  }


  showRTD(){
    if (this.mapRtd) {
      if (this.route.days[this.select_day-1].realTimeData == null) {
        this.serviceManagerProvider.presentToast("No existen datos reales en la ruta");
      } else {
        let data = this.route.days[this.select_day-1].realTimeData.replace(/'/g, '"');
        let jsonString = '{"rtd":' + data + '}';
        let rtd =  JSON.parse(jsonString);
        for (let index = 0; index < rtd.rtd.length - 1; index++) {
          
          this.serviceManagerProvider.getGoogleService().getPointsRoute(rtd.rtd[index].lat, rtd.rtd[index].lng, rtd.rtd[index+1].lat, rtd.rtd[index+1].lng).then(
            coords=> {
              this.map.addPolyline({
                points: coords,
                color: '#3274a585',
                width: 6,
                geodesic: true
              }).then(
                (polyline: Polyline) => {
                  this.rtdpoly.push(polyline);
                }
              );
            }
          );
        }
        this.map.moveCamera({
          target: {
            lat: rtd.rtd[0].lat,
            lng: rtd.rtd[0].lng
          },
          zoom: 18
        });
      }
      
    } else {
      for (let poly of this.rtdpoly) {
        poly.remove();
      }
      this.rtdpoly = [];
    }
    
  }

  createInfoWindow(stayPlaceOrEvent, stay) {
    let html = "<ion-card class='infowindow'><div><div id='iw-col1'><p class='iw-col1-text'>" + stay.order + "</p></div><div id='iw-col2'><div class='iw-col2-row1'><p class='iw-col2-row1-text'>" + stayPlaceOrEvent.name + "</p></div><div class='iw-col2-row2'><p class='iw-col2-row2-text'>" + stayPlaceOrEvent.address + "</p></div><div class='iw-col2-row3'><p class='iw-col2-row3-text'>Parada: " + this.convertMsToString(stay.time) + "</p></div></div></div></ion-card>";
    return html;
  }

  createEventInfoWindow(event) {
    let html = "<ion-card class='infowindow'><div><div id='iw-col1-event'><p class='iw-col1-text'>E</p></div><div id='iw-col2'><div class='iw-col2-row1'><p class='iw-col2-row1-text'>" + event.name + "</p></div><div class='iw-col2-row2'><p class='iw-col2-row2-text'>" + event.address + "</p></div><div class='iw-col2-row3'><p class='iw-col2-row3-text'>De: " + this.getHourAsString(event.startHour) + " a " + this.getHourAsString(event.endHour) + "</p></div></div></div></ion-card>";
    return html;
  }

  showRouteInMap(day) {
    if (this.mapReady) {
      for (let index = 0; index < day.stays.length - 1; index++) {
        this.serviceManagerProvider.getGoogleService().getPointsRoute(day.stays[index].place ? day.stays[index].place.lat : day.stays[index].eventPlace.lat,
          day.stays[index].place ? day.stays[index].place.lng : day.stays[index].eventPlace.lng,
          day.stays[index + 1].place ? day.stays[index + 1].place.lat : day.stays[index + 1].eventPlace.lat,
          day.stays[index + 1].place ? day.stays[index + 1].place.lng : day.stays[index + 1].eventPlace.lng).then(
          coords => {
            this.map.addPolyline({
              points: coords,
              color: '#e78f8f',
              width: 4,
              geodesic: true
            });
          },
          err => {
            console.log(err);
          }
          );
      }
    }

  }

  change(index) {
    let labelDiv = document.getElementById("label");
    this.index = index;
    if (this.mapReady) {
      this.map.moveCamera({
        target: {
          lat: this.markerList[this.index].marker.getPosition().lat,
          lng: this.markerList[this.index].marker.getPosition().lng
        },
        zoom: 18
      });
      for (let inf of this.infoWindowList) {
        inf.close();
      }
      this.markerList[this.index].marker.trigger(GoogleMapsEvent.MARKER_CLICK);
    }
  }


  ngOnDestroy() {
    for (let inf of this.infoWindowList) {
      inf.close();
    }
    this.infWindowEvent.close();
    this.infoWindowList = null;
    if (this.mapReady) {
      this.map.remove();
    }
  }

  convertMsToString(miliseconds) {
    let date = moment.duration(miliseconds, 'milliseconds');
    return date.hours() + "h " + date.minutes() + "m";
  }

  getHourAsString(hour) {
    return moment.utc(hour).format("HH:mm");
 }
}
