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

  private isEvent: boolean = false;
  private eventPlace;

  private index = 0;
  private route;
  private markerList = [];
  private infoWindowList = [];
  private coords = [];

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
    return moment(this.route.startDate + oneDayInMs * (this.select_day-1)).utc().format("DD [de] MMMM [de] YYYY");;
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
      this.showDayInMap(this.route.days[this.select_day-1]);
      this.showRouteInMap(this.route.days[this.select_day-1]);
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

      this.map = this.googleMaps.create(element);
      this.map.one(GoogleMapsEvent.MAP_READY).then(
        () => {
          this.mapReady = true;
          this.showDayInMap(this.route.days[this.select_day-1]);
          this.showRouteInMap(this.route.days[this.select_day-1]);
        }
      );
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
        let html = this.createInfoWindow(pos, stayPlaceOrEvent.name);
        infoWindow.setContent(html);
        this.infoWindowList.push(infoWindow);
        this.coords.push({
          lat: stayPlaceOrEvent.lat,
          lng: stayPlaceOrEvent.lng
        });
        let m = this.map.addMarker({

          title: "A",
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
        if (pos == 0) {
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


  createInfoWindow(pos, name) {
     //let html = "<ion-card class='infowindow'><ion-row ><ion-col id='iw-col1' col-3 ><p class='iw-col1-text'>1</p></ion-col><ion-col id='iw-col2' col-8><ion-row class='iw-col2-row1'><p class='iw-col2-row1-text'>Catedral asd asd asd as d</p></ion-row><ion-row class='iw-col2-row2'><p class='iw-col2-row2-text'>Calle Real</p></ion-row></ion-col><ion-row class='iw-row2'><p class='iw-row2-text'>Parada: 1h 15m</p></ion-row></ion-row></ion-card>";
     let position = pos + 1;
     let html = "<ion-card class='infowindow'><div><div id='iw-col1'><p class='iw-col1-text'>" + position + "</p></div><div id='iw-col2'><div class='iw-col2-row1'><p class='iw-col2-row1-text'>" + name + "</p></div><div class='iw-col2-row2'><p class='iw-col2-row2-text'>Calle Real</p></div><div class='iw-col2-row3'><p class='iw-col2-row3-text'>Parada: 1h 15m</p></div></div></div></ion-card>";
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
    this.infoWindowList = null;
  }
}
