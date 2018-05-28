import { Injectable, } from '@angular/core';
import 'rxjs/add/operator/map';
import {App} from 'ionic-angular';
import { SERVER_PORT, HTTP_PROTOCOL } from './config';
import { RouteServiceProvider } from './route-service/route-service';
import { PlaceServiceProvider } from './place-service/place-service';
import { UserServiceProvider } from './user-service/user-service';
import { UserServiceAuthProvider } from './user-service/user-service-auth';
import { EventServiceProvider } from './event-service/event-service';
import { CategoryServiceProvider } from './category-service/category-service';
import { GoogleServiceProvider } from './google-service/google-service';
import { FoursquareServiceProvider } from './foursquare-service/foursquare-service';
import { AuthServiceProvider } from '../auth-service/auth-service';
import { NativeStorage } from '@ionic-native/native-storage';
import { Toast } from '@ionic-native/toast';
import { AlertController, ToastController, LoadingController } from 'ionic-angular';



/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ServiceManagerProvider {


  private loading = null;

  constructor(private app: App, private toast: Toast, private toastCtrl: ToastController, private routeServiceProvider: RouteServiceProvider, private userServiceProvider:UserServiceProvider, private userServiceAuthProvider:UserServiceAuthProvider, 
    private googleServiceProvider: GoogleServiceProvider, private foursquareServiceProvider: FoursquareServiceProvider,
    private placeServiceProvider: PlaceServiceProvider, private categoryService: CategoryServiceProvider, private authServiceProvider: AuthServiceProvider, private eventService: EventServiceProvider, private nativeStorage: NativeStorage, private loadingCtrl: LoadingController, private alertCtrl: AlertController) {}


  getAuthService() {
    return this.authServiceProvider;
  }

  getRouteService() {
      return this.routeServiceProvider;
  }

  getUserService() {
      return this.userServiceProvider;
  }

  getUserServiceAuth() {
    return this.userServiceAuthProvider;
}

  getGoogleService() {
    return this.googleServiceProvider;
  }

  getFoursquareService() {
    return this.foursquareServiceProvider;
  }

  getPlaceService() {
    return this.placeServiceProvider;
  }

  getCategoryService() {
    return this.categoryService;
  }

  getEventService() {
    return this.eventService;
  }

  handleError(err) {
      if (err.json().type == "ExpiredJwtToken") {
        let refreshToken;
        this.nativeStorage.getItem('user')
        .then(
          data => {
            refreshToken = data.refreshToken;
            this.userServiceProvider.refreshToken(refreshToken).subscribe(
              data => {
                this.authServiceProvider.updateUser(data.json().name, data.json().token, data.json().refreshToken);
                this.presentNativeToast("No se pudo ejecutar la acción, intentelo de nuevo.");
              },
              err => {
                this.getAuthService().logout();
                this.app.getActiveNav().setRoot("LoginPage");
              }
            );
          },
          error => {
            this.getAuthService().logout();
            this.app.getActiveNav().setRoot("LoginPage");
          }
        );
      }
      if (err.status == 403) {
        this.showError(err.json().message, "No eres el propietario de la ruta. No puedes realizar está acción.");
      }
      console.log(err);
  }


  showError(text, subtext) {
    this.dismissLoading();
    let alert = this.alertCtrl.create({
      title: text,
      subTitle: subtext,
      buttons: ['OK']
    });
    alert.present();
  }

  presentToast(text) {
    this.dismissLoading();
    let toast = this.toastCtrl.create({
      message: text,
      duration: 2000,
      position: 'bottom'
    });
  
    toast.present();
  }

  presentNativeToast(text) {
    this.dismissLoading();
    this.toast.show(text, '3000', 'bottom').subscribe(
      toast => {
      }
    );
  }

  showLoading() {
    this.loading =  this.loadingCtrl.create({
      spinner: 'dots',
      content: 'Por favor espera...',
      dismissOnPageChange: true
    });
    this.loading.present();
  }

  dismissLoading() {
    if (this.loading != null) {
      this.loading.dismiss();
    }
  }
}
