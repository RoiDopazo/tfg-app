import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController, AlertController, LoadingController, PopoverController, MenuController  } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import { Events } from 'ionic-angular';

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

  private category;
  private selectedCat;
  private subcategory;
  private selectedSubCat;
  private limit=5;

  private route;
  private places;
  private selectedPlaces = [];
  private num = 3;
  private loading;

  constructor(public popoverCtrl: PopoverController, public menuCtrl: MenuController, public loadingCtrl: LoadingController, public events: Events, public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private alertCtrl: AlertController, private serviceManagerProvider: ServiceManagerProvider) {
    this.route = this.navParams.get("param1");
    this.selectedCat = "";
    this.selectedSubCat = "";
    
    this.presentLoading();
    this.getInitialPlaces();
  }

  
  getSubCategories() {
    for (let cat of this.category) {
        if (cat.id_foursquare == this.selectedCat) {
            this.subcategory = cat.sub_categorias;
        }
    }
}

  getInitialPlaces() {
    this.serviceManagerProvider.getFoursquareService().getPlacesByCity(this.route.id, this.route.city, 8, "true").subscribe(
      data => {
        this.loading.dismiss();
        this.places = data.json();
        
      },
      err => console.log(err)
    );
  }


  checkSelectedPlace(idFoursquare) {
    return true;
    
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
    console.log(this.places);
    console.log(place);
    alert.setTitle('Indique los días');
    for (let day of this.route.days) {
      let check = false;
      for (let places of day.places) {
        if (places.place.idFoursquare == place.idFoursquare){
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
      handler: (data: any) => {
          this.serviceManagerProvider.getRouteService().batchCreateDelete(this.route.id, place.assignedDays, data, place).subscribe(
            data => {
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


  openModalMap() {
    let mapModal = this.modalCtrl.create("MapPage", {
      places: this
    });
  }

}
