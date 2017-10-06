import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the MainPanelPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-main-panel',
  templateUrl: 'main-panel.html',
})
export class MainPanelPage {

  private route;
  private photo;

  constructor(public navCtrl: NavController, public navParams: NavParams) {

    this.route = navParams.get('param1'); 
    this.photo = navParams.get('param4');
    console.log(this.route);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MainPanelPage');
  }

}
