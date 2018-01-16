import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  LatLng,
  CameraPosition,
  MarkerOptions,
  Marker,
  HtmlInfoWindow,
  GoogleMapOptions,
  MapType
} from '@ionic-native/google-maps';
import { ServiceManagerProvider } from '../../providers/services/service-manager';


/**
 * Generated class for the MapModalPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-map-modal',
  templateUrl: 'map-modal.html',
})
export class MapModalPage {

  private mapReady: boolean = false;
  private map;
  private route;
  private eventPlace

  constructor(public navCtrl: NavController, public navParams: NavParams, private googleMaps: GoogleMaps, private serviceManagerProvider: ServiceManagerProvider) {
    
  }

  ionViewDidLoad() {
    this.initMap();
  }

  initMap() {
    let element: HTMLElement = document.getElementById('mapMap');

    let mapOptions: GoogleMapOptions = {
      mapType: 'MAP_TYPE_ROADMAP',
      controls: {
        compass: true,
        myLocationButton: false,
        indoorPicker: false,
        zoom: true
      },
      camera: {
        target: {
          lat: 43.0741904,
          lng: -89.3809802
        },
        zoom: 14,
        tilt: 20
      },
      gestures: {
        scroll: true,
        tilt: true,
        rotate: true,
        zoom: true
      },
      preferences: {
        zoom: {
          minZoom: 8,
          maxZoom: 16
        },
        building: false
      }
    }
    this.map = this.googleMaps.create(element, mapOptions);
    this.map.one(GoogleMapsEvent.MAP_READY).then(
      () => {
        this.mapReady = true;
        this.getCoords();
      }
    );
  }

  getCoords() {
    this.serviceManagerProvider.getGoogleService().getPointsRoute(43.0741904, -89.3809802, 43.0735904, -89.3814802).then(
      coords => {
        console.log(coords);
      },
      err => {
        console.log(err);
      }
    );
  }

}
