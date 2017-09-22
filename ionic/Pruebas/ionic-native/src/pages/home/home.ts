import { Component } from '@angular/core';
import { NavController, IonicPage } from 'ionic-angular';
import {
 GoogleMaps,
 GoogleMap,
 GoogleMapsEvent,
 LatLng,
 CameraPosition,
 MarkerOptions,
 Marker
} from '@ionic-native/google-maps';
import { ListapiPage } from '../listapi/listapi';
import { AuthService } from '../../providers/auth-service';
import { LocationTracker } from '../../providers/locationtracker';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  listPage = ListapiPage;
  public user;

  constructor(public navCtrl: NavController, public googleMaps: GoogleMaps, private auth: AuthService, private locationTracker: LocationTracker ) {
    this.user = this.auth.getUserInfo();
  }


  ngAfterViewInit() {
    this.loadMap();
  } 

  loadMap() {
  // make sure to create following structure in your view.html file
  // and add a height (for example 100%) to it, else the map won't be visible
  // <ion-content>
  //  <div #map id="map" style="height:100%;"></div>
  // </ion-content>

  // create a new map by passing HTMLElement
  let element: HTMLElement = document.getElementById('map');

  let map: GoogleMap = this.googleMaps.create(element);

  // listen to MAP_READY event
  // You must wait for this event to fire before adding something to the map or modifying it in anyway
  map.one(GoogleMapsEvent.MAP_READY).then(
    () => {
      console.log('Map is ready!');
      // Now you can add elements to the map like the marker
    }
  );

  // create LatLng object
  let ionic: LatLng = new LatLng(43.0741904,-89.3809802);

  // create CameraPosition
  let position: CameraPosition = {
    target: ionic,
    zoom: 18,
    tilt: 30
  };

  // move the map's camera to position
  map.moveCamera(position);

  // create new marker
  let markerOptions: MarkerOptions = {
    position: ionic,
    title: 'Ionic'
  };

  let marker = map.addMarker(markerOptions)
    .then((marker: Marker) => {
        marker.showInfoWindow();
      });
  }

  start(){
    this.locationTracker.startTracking();
  }
 
  stop(){
    this.locationTracker.stopTracking();
  }

}
