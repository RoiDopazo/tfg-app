import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController, AlertController, LoadingController, PopoverController, MenuController } from 'ionic-angular';
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

  private select = "list";
  private select_filter = "rec";

  private lat = "";
  private lng = "";
  private radius = 300;
  private section = "topPicks";
  private query = "";
  private query2 = "";
  private limit = 5;
  private sortByDistance = 0;
  private price = "";
  private photo = false;

  private categories;
  private selectedCat = "";
  private subcategories;
  private selectedSubCat = "";

  private route;
  private places;
  private selectedPlaces = [];
  private num = 3;
  private loading;

  private markerList = [];
  private infoWindowList = [];
  private coords = [];

  private markerEvent;
  private infWindowEvent;

  private mapReady: boolean = false;
  private map;

  constructor(public popoverCtrl: PopoverController, public menuCtrl: MenuController, public loadingCtrl: LoadingController, private googleMaps: GoogleMaps, public events: Events, public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private alertCtrl: AlertController, private serviceManagerProvider: ServiceManagerProvider) {
    this.route = this.navParams.get("param1");
    this.serviceManagerProvider.getCategoryService().getAllCategories().subscribe(
      data => {
        this.categories = data.json();
      },
      err => console.log(err)
    );
    this.lat = this.route.lat;
    this.lng = this.route.lng;


    this.refreshRecommended(false);
  }

  ionViewDidLoad() {
    //this.initMap();

  }

  ngOnDestroy() {
    for (let inf of this.infoWindowList) {
      inf.close();
    }
    this.infoWindowList = null;
    if (this.mapReady) {
      this.map.remove();
    }
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

    this.map = this.googleMaps.create(element, { styles: style });
    this.map.one(GoogleMapsEvent.MAP_READY).then(
      () => {
        this.mapReady = true;
        this.showPlacesInMap(false);
        
      }
    );
  }

  filterHere() {
    let c = this.map.getCameraPosition();
    this.lat = c.target.lat;
    this.lng = c.target.lng;
    if (this.select_filter == "rec") {
      this.refreshRecommended(false);
    } else if (this.select_filter == "exp") {
      this.refreshExplore(false);
    }
  }

  showPlacesInMap(refresh) {
    this.infoWindowList = [];
    this.markerList = [];
    if (this.mapReady) {
      if (refresh) {
        this.map.clear();
      }
      let num = 0;
      let color;
      for (let place of this.places) {
        if (place.assignedDays.length > 0) {
          color = "green"
        } else {
          color = "blue";
        }

        let infoWindow = new HtmlInfoWindow;
        let html = this.createInfoWindow(place);
        infoWindow.setContent(html);
        this.infoWindowList.push(infoWindow);
        this.coords.push({
          lat: place.lat,
          lng: place.lng
        });

        let m = this.map.addMarker({
          icon: color,
          position: {
            lat: place.lat,
            lng: place.lng
          }
        }).then(
          marker => {
            this.markerList.push({ marker: marker, pos: num });
            for (let mark of this.markerList) {
              mark.marker.on(GoogleMapsEvent.MARKER_CLICK).subscribe(
                () => {
                  for (let inf of this.infoWindowList) {
                    inf.close();
                  }
                  this.infoWindowList[mark.pos].open(mark.marker);
                }
              );
            }
            num = num + 1;
          }
          );
      }
      if (this.places != undefined) {
        this.map.moveCamera({
          target: {
            lat: this.lat,
            lng: this.lng
          },
          zoom: 17
        });
      }
    }
  }

  private createInfoWindow(place) {
    let html = "<ion-card class='infowindow'><div><div id='iw-col1'><ion-thumbnail item-start><img *ngIf='" + place.categoryIcon + "' class='imgicon2' src='" + place.categoryIcon + "'></ion-thumbnail></div><div id='iw-col2'><div class='iw-col2-row1'><p class='iw-col2-row1-text'>" + place.name + "</p></div><div class='iw-col2-row2'><p class='iw-col2-row2-text'>" + place.categoryName + "</p></div><div class='iw-col2-row2'><p class='iw-col2-row2-text'>Asignado a " + place.assignedDays.length + " días</p></div></div></div></ion-card>";
    return html;
  }

  getDays(idFoursquare) {
    this.num = this.num + 1;
    return this.num;
  }

  presentFilters() {
    this.menuCtrl.toggle();
  }


  addPlace(place) {
    this.doAlertInsertToDays(place);
    if ((this.selectedPlaces.indexOf(place.idFoursquare)) == -1) {
      return true;
    } else {
      return false;
    }
  }


  doAlertInsertToDays(place) {
    let alert = this.alertCtrl.create();
    alert.setTitle('Indique los días');
    for (let day of this.route.days) {
      let check = false;
      for (let stay of day.stays) {
        if (stay.place != undefined && stay.place.idFoursquare == place.idFoursquare) {
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
    alert.addButton('Cancelar');
    alert.addButton({
      text: 'Aceptar',
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

  getSubCategories() {
    this.selectedSubCat = "";
    for (let cat of this.categories) {
      if (cat.id_foursquare == this.selectedCat) {
        this.subcategories = cat.subCategories;
      }
    }
  }


  refreshRecommended(toogleMenu) {
    this.presentLoading();
    this.serviceManagerProvider.getFoursquareService().getRecommendedPlaces(this.route.id, this.lat, this.lng, this.radius, this.section, this.query, this.limit, this.sortByDistance, this.price, this.photo).subscribe(
      data => {
        this.places = data.json();
        this.showPlacesInMap(true);
        this.loading.dismiss();
        if (toogleMenu) {
          this.menuCtrl.toggle();
        }
      },
      err => {
        this.loading.dismiss();
      }
    );
  }

  refreshExplore(toogleMenu) {
    this.presentLoading();
    let catToSearch = this.selectedSubCat == "" ? this.selectedCat : this.selectedSubCat;
    this.serviceManagerProvider.getFoursquareService().searchPlaces(this.route.id, this.lat, this.lng, this.radius, this.query2, this.limit, catToSearch, this.photo).subscribe(
      data => {
        this.places = data.json();
        this.showPlacesInMap(true);
        this.loading.dismiss();
        if (toogleMenu) {
          this.menuCtrl.toggle();
        }
      },
      err => {
        this.loading.dismiss();
      }
    );

  }

}
