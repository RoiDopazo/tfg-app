import { Component, ElementRef, ViewChild } from '@angular/core';
import { NavController, IonicPage, ModalController, Loading, LoadingController } from 'ionic-angular';
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
import { CategoryService } from '../../../../../services/CategoryService';

@Component({
    selector: 'page-searchview1',
    templateUrl: 'searchview1.html'
})
export class SearchView1Page {

    private place_to_search;
    private places;
    private category;
    private selectedCat;
    private subcategory;
    private selectedSubCat;
    private mainsearch = 'options';
    private tabBarElement;
    private tabbar;
    private photocheck = 'false';
    public user;
    private map: GoogleMap;
    private loading: Loading;
    @ViewChild('googlemap') theMap: ElementRef;

    constructor(public navCtrl: NavController, public googleMaps: GoogleMaps, private auth: AuthService, private modalCtrl: ModalController, private fsService: FoursquareService, private categoryService: CategoryService, private loadingCtrl: LoadingController) {
        this.user = this.auth.getUserInfo();
        this.selectedCat = "";
        this.selectedSubCat = "";
        this.tabbar = document.querySelectorAll(".tabbar");
        if (this.tabbar != null) {
            Object.keys(this.tabbar).map((key) => {
                this.tabbar[key].style.display = 'none';
            });
        }
        this.categoryService.getAllCategories().subscribe(
            data => {
                this.category = data.json();
            },
            err => {
                console.log("err -- category place");
            },
            () => {
                console.log("() -- category Place");
            }
        );
    }

    ngOnDestroy() {
        if (this.tabbar != null) {
            Object.keys(this.tabbar).map((key) => {
                this.tabbar[key].style.display = '';
            });
        }
    }

    ngAfterViewInit() {
        this.hideMap();
        this.initMap();  
    }

    showMap() {
        this.theMap.nativeElement.hidden = false;
    }

    hideMap() {
        this.theMap.nativeElement.hidden = true;
    }

    initMap() {
        // create a new map by passing HTMLElement
        let element: HTMLElement = document.getElementById('googlemap');
        
        let mapOptions = {
            camera: {
              zoom: 18,
              tilt: 30,
              gestures: {
                scroll: true,
                tilt: true,
                rotate: true,
                zoom: true
              }
            }
          };
        this.map = this.googleMaps.create(element, mapOptions);
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
        for (let p of this.places) {
            
            // create LatLng object
            let pos: LatLng = new LatLng(p.lat, p.lng);
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
        this.map.clear();
        this.showLoading();
        let cat: String;
        if (this.selectedSubCat == "") {
            cat = this.selectedCat;
        } else {
            cat = this.selectedSubCat; 
        }
            
        this.fsService.getPlaces(this.place_to_search, 10, cat, this.photocheck).subscribe(
            data => {
                this.places = data.json();
                this.mainsearch = "places";
                console.log(this.places);
                this.loading.dismiss();
            },
            err => {
                console.log("err -- find place");
            },
            () => {
                console.log("() -- find Place");
            }
        );
    }


    getSubCategories() {
        for (let cat of this.category) {
            if (cat.id_foursquare == this.selectedCat) {
                this.subcategory = cat.sub_categorias;
            }
        }
    }


    showLoading() {
        this.loading = this.loadingCtrl.create({
          spinner: 'dots',
          content: 'Please wait...',
          dismissOnPageChange: true
        });
        this.loading.present();
      }
}

