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

  private index = 0;
  private numDay;
  private route;
  private markerList = [];
  private infoWindowList = [];
  private coords = [];

  private mapReady: boolean = false;
  private map;
  start = 'chicago, il';
  end = 'chicago, il';
  directionsService = new google.maps.DirectionsService;
  directionsDisplay = new google.maps.DirectionsRenderer;
  @ViewChild('mapMap') theMap: ElementRef;

  constructor(public navCtrl: NavController, public navParams: NavParams, private googleMaps: GoogleMaps, private serviceManagerProvider: ServiceManagerProvider) {
    this.route = this.navParams.get("route");
    this.numDay = this.navParams.get("numDay");

    if (this.numDay == undefined) {
      this.numDay = 1;
    }

    this.initDayVariables();
  }

  ionViewDidLoad() {
    //this.initMap();
    let element: HTMLElement = document.getElementById("s_button1");
    element.classList.add("segment-activated", "activated");
  }

  removeActive() {
    let element: HTMLElement = document.getElementById("s_button1");
    if (element != null) {
      element.classList.remove("segment-activated", "activated");
    }
  }

  initDayVariables() {

    if (this.numDay == 1) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus =  this.current_day + 1;
    } else if (this.route.numDays == this.numDay) {
      this.current_day = this.numDay - 1;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
    } else {
      this.current_day = parseInt(this.numDay);
      this.current_day_less = parseInt(this.numDay) - 1;
      this.current_day_plus = parseInt(this.numDay) + 1; 
    }
    this.select_day = this.numDay;
  }


  oneMoreDay() {
    let element: HTMLElement = document.getElementById("s_button" + (this.select_day - 1));
    console.log(element);
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
    console.log(element);
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
          this.showDayInMap(this.route.days[this.numDay-1]);
          this.showRouteInMap(this.route.days[this.numDay-1]);
        }
      );
  }

  showDayInMap(day) {
    this.map.clear();
    if (this.mapReady) {
      let pos = 0;
      let num = 0;
      for (let place of day.places) {
        let infoWindow = new HtmlInfoWindow;
        let html = "<p>" + place.place.name + "</p>";
        infoWindow.setContent(html);
        this.infoWindowList.push(infoWindow);
        this.coords.push({
          lat: place.place.lat,
          lng: place.place.lng
        });
        let m = this.map.addMarker({
          icon: 'red',
          animation: 'DROP',
          position: {
            lat: place.place.lat,
            lng: place.place.lng
          }
        }).then(
          marker => {
            this.markerList.push({ marker: marker, pos: num });
            if (num == day.places.length - 1) {
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
              lat: place.place.lat,
              lng: place.place.lng
            },
            zoom: 18
          });
        }
        pos = pos + 1;
      }
    }

  }


  getPointsRoute(oriLat, oriLng, destLat, destLng) {
    let directionsService = new google.maps.DirectionsService;
    directionsService.route({
      origin: {
        lat: oriLat,
        lng: oriLng
      },
      destination: {
        lat: destLat,
        lng: destLng
      },
      travelMode: google.maps.TravelMode['WALKING']
    }, (res, status) => {

      if (status == google.maps.DirectionsStatus.OK) {
        let coords = [];
        coords.push({
          lat: oriLat,
          lng: oriLng
        });
        coords.push({
          lat: res.routes[0].legs[0].steps[0].start_location.lat(),
          lng: res.routes[0].legs[0].steps[0].start_location.lng()
        });
        for (let step of res.routes[0].legs[0].steps) {
          coords.push({
            lat: step.end_location.lat(),
            lng: step.end_location.lng()
          });
        }
        coords.push({
          lat: destLat,
          lng: destLng
        });
        console.log(res);
        console.log(coords);

        this.map.addPolyline({
          points: coords,
          color: '#e78f8f',
          width: 4,
          geodesic: true
        });
      } else {
        console.warn(status);
      }
    });
  }

  showRouteInMap(day) {
    if (this.mapReady) {
      for (let index = 0; index < day.places.length - 1; index++) {
        this.getPointsRoute(day.places[index].place.lat, day.places[index].place.lng, day.places[index + 1].place.lat, day.places[index + 1].place.lng)
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
  }
}
