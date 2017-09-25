import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

/**
 * Generated class for the MaintabPage tabs.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-maintab',
  templateUrl: 'maintab.html'
})
export class MaintabPage {

  tab_1Root = 'Tab_1Page'
  tab_2Root = 'Tab_2Page'
  tab_3Root = 'Tab_3Page'
  tab_4Root = 'Tab_4Page'
  tab_5Root = 'Tab_5Page'


  constructor(public navCtrl: NavController) {}

}
