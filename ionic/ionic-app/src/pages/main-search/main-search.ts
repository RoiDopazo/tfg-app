import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ActionSheetController, reorderArray  } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';

/**
 * Generated class for the MainSearchPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-main-search',
  templateUrl: 'main-search.html',
})
export class MainSearchPage {

  private current_day_less;
  private current_day_plus;
  private current_day;
  private select_day;
  private route;

  private city_to_search;

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider, private actionSheetCtrl: ActionSheetController) {
    
    this.route = navParams.get('param1'); 
    this.initDayVariables();
  }


  initDayVariables() {
    if (this.route.numDays >= 3) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    } else if (this.route.numDays == 2) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    } else if (this.route.numDays == 1) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    }
   
  }

  oneMoreDay() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day-1));
    if (!(this.route.numDays == this.current_day_plus)) {
      this.current_day_less = this.current_day_less + 1;
      this.current_day = this.current_day + 1;
      this.current_day_plus = this.current_day_plus + 1;   
      this.select_day = this.current_day; 
      if (element != null) {
        element.classList.add("segment-activated", "activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated", "activated");
      }
    }
  }

  oneDayLess() {
    let value = (parseInt(this.select_day)+1);
    let element: HTMLElement = document.getElementById("select_button" + value);
    if (this.current_day_less > 1) {
      this.current_day_less = this.current_day_less - 1;
      this.current_day = this.current_day - 1;
      this.current_day_plus = this.current_day_plus - 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.add("segment-activated", "activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated", "activated");
      }
    }
  }




  reorderItems(current_day, indexes) {
    this.route.days[current_day-1].places = reorderArray(this.route.days[current_day-1].places, indexes);
  }

  showActionsAdd() {
    let actionSheet = this.actionSheetCtrl.create({
      buttons: [
        {
          text: 'Lugar',
          handler: () => {
            this.navCtrl.push("MainPlacesPage", {
              param1: this.route
            });
          }
        },{
          text: 'Evento',
          handler: () => {
            console.log('Añadir Evento');
          }
        },{
          text: 'Alojamiento',
          handler: () => {
            console.log('Añadir Alojamiento');
          }
        },{
          text: 'Hora de Comienzo',
          handler: () => {
            console.log('Añadir Hora Comienzo');
          }
        }
      ]
    });
    actionSheet.present();

  }
}
