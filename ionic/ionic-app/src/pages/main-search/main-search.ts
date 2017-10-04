import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

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

  private current_day_less = 1;
  private current_day_plus = 3;
  private num_days = 5;
  private current_day = 2;
  private select_day;
  private city_to_search;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.current_day = 2;
    this.current_day_less = this.current_day - 1;
    this.current_day_plus = this.current_day + 1;
    this.select_day = this.current_day_less;
    this.city_to_search = navParams.get('param1'); 
    console.log(this.city_to_search);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MainSearchPage');
  }

  oneMoreDay() {
    console.log(this.select_day);
    let x = (this.select_day-1);
    console.log(x);
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day-1));
    console.log(element);
    if (!(this.num_days == this.current_day_plus)) {
      this.current_day_less = this.current_day_less + 1;
      this.current_day = this.current_day + 1;
      this.current_day_plus = this.current_day_plus + 1;   
      this.select_day = this.current_day; 
      if (element != null) {
        element.classList.add("segment-activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated");
      }
    }
  }

  oneDayLess() {
    console.log(this.select_day);
    let value = (parseInt(this.select_day)+1);
    let element: HTMLElement = document.getElementById("select_button" + value);
    console.log(element);
    if (this.current_day_less > 1) {
      this.current_day_less = this.current_day_less - 1;
      this.current_day = this.current_day - 1;
      this.current_day_plus = this.current_day_plus - 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.add("segment-activated");
      }
    } else {
      element.classList.remove("segment-activated");
    }
  }















  oneMoreDay2() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day-1));
    if (!(this.num_days == this.current_day_plus)) {
      this.current_day_less = this.current_day_less + 1;
      this.current_day = this.current_day + 1;
      this.current_day_plus = this.current_day_plus + 1;   
      this.select_day = this.current_day; 
      if (element != null) {
        element.classList.add("segment-activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated");
      }
    }
  }

  oneDayLess2() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day+1));
    if (this.current_day_less > 1) {
      this.current_day_less = this.current_day_less - 1;
      this.current_day = this.current_day - 1;
      this.current_day_plus = this.current_day_plus - 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.remove("segment-activated");
      }
    } else {
      if (element != null) {
        element.classList.add("segment-activated");
      }
    }
  }

}
