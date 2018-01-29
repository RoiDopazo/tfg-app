import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController, AlertController, LoadingController, PopoverController, MenuController  } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import { Events } from 'ionic-angular';
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

/**
 * Generated class for the MainPlacesPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-main-places',
  templateUrl: 'main-places.html',
})
export class MainPlacesPage {

  private select="list";
  private select_filter="rec";

  private lat = "";
  private lng = "";
  private radius = 300;
  private section = "topPicks";
  private query = "";
  private limit = 5;
  private sortByDistance = 1;
  private price = "";
  private photo = false;

  private category;
  private selectedCat;
  private subcategory;
  private selectedSubCat;

  private route;
  private places;
  private selectedPlaces = [];
  private num = 3;
  private loading;

  private mapReady: boolean = false;
  private map;

  constructor(public popoverCtrl: PopoverController, public menuCtrl: MenuController, public loadingCtrl: LoadingController, private googleMaps: GoogleMaps, public events: Events, public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private alertCtrl: AlertController, private serviceManagerProvider: ServiceManagerProvider) {
    this.route = this.navParams.get("param1");
    this.selectedCat = "";
    this.selectedSubCat = "";
    this.lat = this.route.lat;
    this.lng = this.route.lng;
    
    this.presentLoading();
    this.getInitialPlaces();
  }

  ionViewDidLoad() {
    //this.initMap();
  }

  initMap() {
    let element: HTMLElement = document.getElementById('mapPlaces');

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
        this.showPlacesInMap();
      }
    );
  }

  showPlacesInMap() {
    if (this.mapReady = true) {
      for (let place of this.places) {
        let m = this.map.addMarker({
          icon: 'blue',
          animation: 'DROP',
          position: {
            lat: place.lat,
            lng: place.lng
          }
        });
      }
    }
  }
  
  getSubCategories() {
    for (let cat of this.category) {
        if (cat.id_foursquare == this.selectedCat) {
            this.subcategory = cat.sub_categorias;
        }
    }
}

  getInitialPlaces() {
    this.serviceManagerProvider.getFoursquareService().getPlacesByCity(this.route.id, this.lat, this.lng, this.radius, this.section, this.query, this.limit, this.sortByDistance, this.price, this.photo).subscribe(
      data => {
        this.loading.dismiss();
        this.places = data.json();
      },
      err => console.log(err)
    );
  }


  addPlace(place) {
    console.log(place);
    this.doAlertInsertToDays(place);
    if ((this.selectedPlaces.indexOf(place.idFoursquare)) == -1) {
      return true;
    } else {
      return false;
    }
  }


  getDays(idFoursquare) {
    this.num = this.num + 1;
    return this.num;
  }


  doAlertInsertToDays(place) {
    let alert = this.alertCtrl.create();
    alert.setTitle('Indique los días');
    for (let day of this.route.days) {
      let check = false;
      for (let stay of day.stays) {
        if (stay.place != undefined && stay.place.idFoursquare == place.idFoursquare){
          check = true;
        }
      }
      alert.addInput({
        type: "checkbox",
        label: "Día " + day.idDay,
        value: day.idDay,
        checked: check
      })
    }
    alert.addButton('Cancel');
    alert.addButton({
      text: 'Okay',
      handler: (newAssignedDays: any) => {
          this.serviceManagerProvider.getRouteService().stay_create_delete_batch(this.route.id, place.assignedDays, newAssignedDays, place).subscribe(
            data => {
              place.assignedDays = newAssignedDays;
              this.events.publish('place:mod', this.route.id);
            },
            err => console.log(err)
          );
      }
    });
    alert.present();
  }
  

  presentLoading() {
    this.loading = this.loadingCtrl.create({
      spinner: 'crescent',
      content: 'Obteniendo lista de lugares...'
    });

    this.loading.present();
  }

  presentFilters() {
    this.serviceManagerProvider.getCategoryService().getAllCategories().subscribe(
      data => {
        this.category = data.json();
        this.menuCtrl.toggle();
      }
    );
  }


}
