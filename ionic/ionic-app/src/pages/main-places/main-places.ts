import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
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

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider) {
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

  addPlace(idFoursquare) {
    this.selectedPlaces.push(idFoursquare);
  }

  checkSelectedPlace(idFoursquare) {
    return true;
    
  }


  add(idFoursquare) {
    if ((this.selectedPlaces.indexOf(idFoursquare)) == -1) {
      return true;
    } else {
      return false;
    }
  }


  getDays(idFoursquare) {
    this.num = this.num + 1;
    console.log(this.num);
    return this.num;
  }
}
