import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, reorderArray  } from 'ionic-angular';
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

  private city_to_search;

  private route = {
    "id": 100,
    "photo": "https://www.deportetotalfm.com/wp-content/uploads/2016/10/fondos-abstractos-para-paginas-web-para-fondo-celular-en-hd-12.jpg",
    "state": "PENDIENTE",
    "num_places": 0,
    "city": "Madrid",
    "country": "España",
    "distance": 0,
    "time": 0,
    "num_days": 5,
    "creation_date": "06/10/2017",
    "start_date": undefined,
    "end_date": undefined,
    "days": [{
      "name": "Día 1",
      "departure_time": undefined,
      "places": [{
        "name": "NH Paseo Del Prado",
        "lat": 40.4165,
        "lng": -3.70256,
        "direccion": "Plaza CÌÁnovas del Castillo, 4",
        "distance": "3km"
      },
      {
        "name": "Puerta del Sol",
        "lat": 40.41689344403009,
        "lng": -3.703383301250136,
        "direccion": "Pl. Puerta del Sol",
        "distance": "1km"
      },
      {
        "name": "Plaza Mayor",
        "lat": 40.41539331961118,
        "lng": -3.707377344074966,
        "direccion": "Pl. Mayor",
        "distance": "7km"
      }]
    },
    {

    },
    {

    }]
  };

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider) {
    console.log(this.route);
    this.initDayVariables();
  }


  initDayVariables() {
    if (this.route.num_days >= 3) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    } else if (this.route.num_days == 2) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    } else if (this.route.num_days == 1) {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
    }
   
  }

  oneMoreDay() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day-1));
    if (!(this.route.num_days == this.current_day_plus)) {
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
    this.route.days[current_day-1].places = reorderArray(this.route.days[current_day-1].places, indexes);

    this.update();
  }

  update() {
    
  }

}
