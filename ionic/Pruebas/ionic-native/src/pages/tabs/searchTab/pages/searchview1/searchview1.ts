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
import { FoursquareService } from '../../../../../services/FoursquareService';


@Component({
    selector: 'page-searchview1',
    templateUrl: 'searchview1.html'
})
export class SearchView1Page {

    private place_to_search;
    private places;
    private mainsearch = 'options';
    public user;
    private map: GoogleMap;

    constructor(public navCtrl: NavController, public googleMaps: GoogleMaps, private auth: AuthService, private modalCtrl: ModalController, private fsService: FoursquareService) {
        this.user = this.auth.getUserInfo();
    }

    ngAfterViewInit() {
        this.initMap();
    }

    initMap() {
        // create a new map by passing HTMLElement
        let element: HTMLElement = document.getElementById('google-map');
        console.log(element);
        this.map = this.googleMaps.create(element);
        console.log(this.map);
        // listen to MAP_READY event
        // You must wait for this event to fire before adding something to the map or modifying it in anyway
        this.map.one(GoogleMapsEvent.MAP_READY).then(
            () => {
                console.log('Map is ready!');
                // Now you can add elements to the map like the marker
            }
        );
    }

    moveToPosition() {
        console.log(this.places);
        for (let p of this.places) {
            
            // create LatLng object
            let pos: LatLng = new LatLng(p.lat, p.lng);
            console.log(pos);
            // create CameraPosition
            let position: CameraPosition = {
                target: pos,
                zoom: 18,
                tilt: 30
            };

            // move the map's camera to position
            this.map.moveCamera(position);

            // create new marker
            let markerOptions: MarkerOptions = {
                position: pos,
                title: p.nombre
            };

            let marker = this.map.addMarker(markerOptions)
                .then((marker: Marker) => {
                    marker.showInfoWindow();
                });
        }

    }



    findPlace() {
        this.fsService.getPlaces(this.place_to_search).subscribe(
            data => {
                this.places = data.json();
            },
            err => {
                console.log("err -- find place");
            },
            () => {
                console.log("() -- find Place");
            }
        );
    }


}

