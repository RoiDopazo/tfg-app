import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import moment from "moment";
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import { AuthServiceProvider } from '../../providers/auth-service/auth-service';
/**
 * Generated class for the MainPanelAboutPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-main-panel-about',
  templateUrl: 'main-panel-about.html',
})
export class MainPanelAboutPage {

  private route;
  private user;
  private isPriv;

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider, private auth: AuthServiceProvider) {
    this.route = this.navParams.get("route");
    this.user = this.auth.getUserInfo().id;
  }


  getDateAsString(date) {
    if (date == null) {
      return "Sin especificar";
    } else {
      return moment(date).utc().format("DD-MMM-YYYY");
    }
  }

  changePriv(priv) {
    this.serviceManagerProvider.showLoading();
    this.serviceManagerProvider.getRouteService().updatepriv(this.route.id, priv).subscribe(
      data => {
        this.route = data.json();
        this.serviceManagerProvider.dismissLoading();
      },
      err => {
        this.serviceManagerProvider.handleError(err);
      }
    );
  }

  convertMsToStringLarge(miliseconds) {

    let date = moment.duration(miliseconds, 'milliseconds');
    if (date.hours() > 0) {
      if (date.hours() == 1) {
        return date.hours() + " hora " + date.minutes() + " min";
      } else {
        return date.hours() + " horas " + date.minutes() + " min";
      }
    } else {
      if (date.minutes() == 0) {
        return date.seconds() + " segundos";
      } else {
        if (date.seconds() == 0) {
          return date.minutes() + " minutos";
        } else {
          return date.minutes() + " min " + date.seconds() + " seg";
        }
      }
    }
  }


  getTravelDistance(distance) {
    if (distance == null) {
      return "0 metros";
    } else if (distance / 1000 >= 1) {
      return distance / 1000 + " km"
    }
    return distance + " metros";
  }

  deleteRoute(route){
    this.serviceManagerProvider.getRouteService().deleteRoute(route.id).subscribe(
      data => {
        this.navCtrl.setRoot("UserInfoTabPage");
        this.serviceManagerProvider.presentNativeToast("Ruta eliminada correctamente");
      },
      err => {
        this.serviceManagerProvider.presentNativeToast("No se pudo eliminar la ruta. Inténtelo de nuevo");
      }
    );
  }

}
