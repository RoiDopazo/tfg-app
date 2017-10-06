import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, reorderArray  } from 'ionic-angular';

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
  private num_days = 3;
  private current_day;
  private select_day;
  private city_to_search;

  private route = {
    nombre: "1",
    day: []
  }

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.initDayVariables();
    this.city_to_search = navParams.get('param1'); 
    console.log(this.city_to_search);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MainSearchPage');
  }


  initDayVariables() {
    if (this.num_days >= 3) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    } else if (this.num_days == 2) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    } else if (this.num_days == 1) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    }
    for (let i=1; i <= this.num_days; i++) {
      let info_day = {
        name: "Día " + i,
        places: [{
          name: "Place1"
        },
        {
          name: "Place2"
        }]
      }
      this.route.day.push(info_day);
    }
  }

  oneMoreDay() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day-1));
    if (!(this.num_days == this.current_day_plus)) {
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
    console.log(current_day);
    this.route.day[current_day-1].places = reorderArray(this.route.day[current_day-1].places, indexes);
  }
}
