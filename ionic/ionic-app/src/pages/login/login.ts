import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, Loading, LoadingController, AlertController} from 'ionic-angular';
import { AuthServiceProvider } from '../../providers/auth-service/auth-service';
/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})


export class LoginPage {
  private loading: Loading;
  private loginCredentials= { username: '', password: '' };

  constructor(public navCtrl: NavController, public navParams: NavParams, private auth: AuthServiceProvider, private loadingCtrl: LoadingController, private alertCtrl: AlertController) {
  }

  public showRegister() {
    this.navCtrl.push("RegisterPage");
  }


  loginLocal() {
    this.showLoading();
    this.auth.login(this.loginCredentials).then(access => {

      if (access) {
        this.navCtrl.setRoot("MaintabPage");
      } else {
        this.showError("Access Denied");
      }
    },
      error => {
        this.showError(error);
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

  showError(text) {
    this.loading.dismiss();

    let alert = this.alertCtrl.create({
      title: 'Error !',
      subTitle: text,
      buttons: ['OK']
    });
    alert.present();
  }
}

