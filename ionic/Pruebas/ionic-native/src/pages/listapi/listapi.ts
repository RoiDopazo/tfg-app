import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { UserService } from '../../services/userservice';

/**
 * Generated class for the ListapiPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */


@Component({
  selector: 'page-listapi',
  templateUrl: 'listapi.html',
  providers: [UserService]
})
export class ListapiPage {

  public datos;
  
  constructor(public navCtrl: NavController, public navParams: NavParams, private userService: UserService) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ListapiPage');
  }

  getData() {
    this.userService.getExample().subscribe(
      data => {
        this.datos = data.json();
      },
      err => console.error(err),
      () => console.log('get data complete')
    );
  }

}
