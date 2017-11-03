import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { global } from './../../../providers/services/config';

/**
 * Generated class for the Tab_4Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-tab-4',
  templateUrl: 'tab-4.html',
})
export class Tab_4Page {


  private ip = global.SERVER_IP;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad Tab_4Page');
  }


  fijarIP() {
    global.fun(this.ip);
  }
}
