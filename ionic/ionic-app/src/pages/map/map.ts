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

  private index = 0;
  private day;
  private markerList = [];
  private infoWindowList = [];

  private mapReady : boolean = false;
  private map: GoogleMap;
  @ViewChild('mapMap') theMap: ElementRef;

  constructor(public navCtrl: NavController, public navParams: NavParams, private googleMaps: GoogleMaps, private serviceManagerProvider: ServiceManagerProvider) {
   this.day = this.navParams.get("day");
  }

  ionViewDidLoad() {
    
    
    
    this.initMap();
  }  

  initMap() {
    let element: HTMLElement = document.getElementById('mapMap');

    this.map = this.googleMaps.create(element);
    this.map.one(GoogleMapsEvent.MAP_READY).then(
      () => {
        this.mapReady = true;
        this.showDayInMap();
      }
    );
  }

  showDayInMap() {
    let pos = 0;
    let num = 0;
    for (let place of this.day.places) {
      let infoWindow = new HtmlInfoWindow;
      let html = "<p>" + place.place.name + "</p>";
      infoWindow.setContent(html);
      this.infoWindowList.push(infoWindow);
      let m = this.map.addMarker({
        icon: 'red',
        animation: 'DROP',
        position: {
          lat: place.place.lat,
          lng: place.place.lng
        }
      }).then(
        marker => {
          console.log(this.markerList);
          this.markerList.push({marker: marker, pos: num});
          if (num == this.day.places.length-1) {
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
        console.log("move");
        this.map.moveCamera({
          target: {
            lat: place.place.lat,
            lng: place.place.lng
          },
          zoom: 18,
          tilt: 80
        });
      }
      pos = pos + 1; 
    }
  }    

  change(index) {
    let labelDiv = document.getElementById("label");
    this.index = index;
    console.log(this.infoWindowList);
    this.map.moveCamera({
      target: {
        lat: this.markerList[this.index].marker.getPosition().lat,
        lng: this.markerList[this.index].marker.getPosition().lng
      },
      zoom: 18,
      tilt: 80
    });
    for (let inf of this.infoWindowList) {
      inf.close();
    }
    this.markerList[this.index].marker.trigger(GoogleMapsEvent.MARKER_CLICK);
  }

}
