import { Component } from '@angular/core';
import { NavController, AlertController, IonicPage, LoadingController, Loading, MenuController } from 'ionic-angular';
import { AuthService } from '../../providers/auth-service';
 
import { HomePage } from '../home/home';

@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})
export class RegisterPage {
  loading: Loading;
  createSuccess = false;
  registerCredentials = { username: '', password: '' };
 
  constructor(private nav: NavController, private auth: AuthService, private alertCtrl: AlertController, private loadingCtrl: LoadingController, private menu:MenuController) { }
 
  public register() {
    this.showLoading();
    this.auth.register(this.registerCredentials).then(success => {
      if (success) {
        this.createSuccess = true;
        this.showPopup("Success", "Account created.");
        this.menu.enable(true, "menu");
      } else {
        this.showPopup("Error", "Problem creating account.");
      }
    },
      error => {
        this.showPopup("Error", error);
      });
  }
  
  showLoading() {
    this.loading = this.loadingCtrl.create({
      spinner: 'dots',
      content: 'Please wait...',
      dismissOnPageChange: true
    });
    this.loading.present();
  }


  showPopup(title, text) {
    let alert = this.alertCtrl.create({
      title: title,
      subTitle: text,
      buttons: [
        {
          text: 'OK',
          handler: data => {
            if (this.createSuccess) {
              this.nav.setRoot(HomePage);
            }
          }
        }
      ]
    });
    alert.present();
  }
}