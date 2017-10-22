import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ModalController, AlertController  } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';

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

  private route;
  private places;
  private selectedPlaces = [];
  private num = 3;

  constructor(public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private alertCtrl: AlertController, private serviceManagerProvider: ServiceManagerProvider) {
    this.route = this.navParams.get("param1");
    this.getInitialPlaces();
  }

  

  getInitialPlaces() {
    this.serviceManagerProvider.getFoursquareService().getPlacesByCity(this.route.id, this.route.city, 8, "true").subscribe(
      data => {
        this.places = data.json();
        console.log(this.places);
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
            data => console.log(data.json()),
            err => console.log(err)
          );
      }
    });
    alert.present();
  }
  
}
