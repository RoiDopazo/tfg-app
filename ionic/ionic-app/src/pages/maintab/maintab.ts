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

  tab_SearchRoot = 'Tab_SearchPage'
  tab_2Root = 'AddRouteTabPage'
  tab_3Root = 'ExploreTabPage'
  tab_4Root = 'UserInfoTabPage'


  constructor(public navCtrl: NavController) {}

}
