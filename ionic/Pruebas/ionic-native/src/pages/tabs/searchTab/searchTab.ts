import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { SearchView1Page } from './pages/searchview1/searchview1';

@Component({
  selector: 'page-searchtab',
  templateUrl: 'searchTab.html',
})


export class SearchTabPage {
  

  public datos;
  
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ListapiPage');
  }



  forward() {
    this.navCtrl.push(SearchView1Page);
  }

}
