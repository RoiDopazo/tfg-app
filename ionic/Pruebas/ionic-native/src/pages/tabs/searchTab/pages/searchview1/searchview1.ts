import { Component, ElementRef, ViewChild } from '@angular/core';
import { NavController, IonicPage, ModalController } from 'ionic-angular';
import {
    GoogleMaps,
    GoogleMap,
    GoogleMapsEvent,
    LatLng,
    CameraPosition,
    MarkerOptions,
    Marker
} from '@ionic-native/google-maps';
import { AuthService } from '../../../../../providers/auth-service';


@Component({
    selector: 'page-searchview1',
    templateUrl: 'searchview1.html'
})
export class SearchView1Page {

    private place_to_search;
    public user;

    constructor(public navCtrl: NavController, public googleMaps: GoogleMaps, private auth: AuthService, private modalCtrl: ModalController) {
        this.user = this.auth.getUserInfo();
    }


    ngAfterViewInit() {
        this.initMap();
    }

    initMap() {

        
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
        let ionic: LatLng = new LatLng(43.0741904, -89.3809802);

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



    findPlace() {
        console.log(this.place_to_search);
    }


}

