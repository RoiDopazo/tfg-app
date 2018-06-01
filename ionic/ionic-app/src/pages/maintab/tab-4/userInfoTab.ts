import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController, PopoverController } from 'ionic-angular';
import { global } from './../../../providers/services/config';
import { ServiceManagerProvider } from '../../../providers/services/service-manager';
import moment from "moment";
/**
 * Generated class for the Tab_4Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-userinfotab',
  templateUrl: 'userinfotab.html',
})
export class UserInfoTabPage {

  private user;
  private state = "pending";
  private routes = [];

  private ip = global.SERVER_IP;

  constructor(public navCtrl: NavController, public navParams: NavParams,
    private popoverCtrl: PopoverController, private serviceManagerProvider:
      ServiceManagerProvider, private alertCtrl: AlertController) {

    this.user = this.serviceManagerProvider.getAuthService().getUserInfo();
    this.serviceManagerProvider.getRouteService().getOwnRoutes(this.state).subscribe(
      data => {
        let datajson = data.json();
        for (let d in datajson) {
          this.routes.push(datajson[d]);
        }
      },
      err => {
        this.serviceManagerProvider.handleError(err);
      }
    );
  }
  change() {
    this.serviceManagerProvider.showLoading();
    this.serviceManagerProvider.getRouteService().getOwnRoutes(this.state).subscribe(
      data => {
        this.serviceManagerProvider.dismissLoading();
        this.routes = [];
        let datajson = data.json();
        for (let d in datajson) {
          this.routes.push(datajson[d]);
        }
      },
      err => {
        this.serviceManagerProvider.handleError(err);
        this.serviceManagerProvider.dismissLoading();
      }
    );
  }


  getDateAsString(date) {
    return moment(date).utc().format("DD-MMM-YYYY");
  }

  openRouteInfo(id) {
    this.navCtrl.push("MainPanelPage", {
      param1: id
    });
  }


  editUserData() {
    this.serviceManagerProvider.getUserServiceAuth().getUserInfo().subscribe(
      data => {
        let userinfo = data.json();
        let alert = this.alertCtrl.create({
          title: 'Modifcar Datos Perosnales',
          inputs: [
            {
              name: 'email',
              placeholder: 'E-Mail',
              value: userinfo.email,
            },
            {
              name: 'password',
              placeholder: 'Contraseña',
              type: 'password'
            },
            {
              name: 'password2',
              placeholder: 'Repetir Contraseña',
              type: 'password'
            }
          ],
          buttons: [
            {
              text: 'Cancelar',
              role: 'cancel',
              handler: data => {
              }
            },
            {
              text: 'Modificar',
              handler: data => {
                if ((data.password == "") || (data.password2 == "")) {
                  this.serviceManagerProvider.presentNativeToast("La contraseña no puede estar vacía");
                  this.editUserData();
                } else {
                  if (data.password == data.password2) {
                    delete data["password2"];
                    this.serviceManagerProvider.getUserServiceAuth().updateUser(userinfo.id, data).subscribe(
                      data => {
                        this.serviceManagerProvider.presentNativeToast("Datos modificados correctamente");
                      },
                      err => {
                        this.serviceManagerProvider.handleError(err);
                      }
                    );
                  } else {
                    this.serviceManagerProvider.presentNativeToast("Las contraseñas no coinciden");
                    this.editUserData();
                  }
                }
              }
            }
          ]
        });
        alert.present();
      },
      err => {
        this.serviceManagerProvider.handleError(err);
      }
    );
  }


  logout() {
    let alert = this.alertCtrl.create({
      title: 'Salir',
      message: 'Está seguro de querer desconectarse de la aplicación?',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: () => {
          }
        },
        {
          text: 'Confirmar',
          handler: () => {
            this.serviceManagerProvider.getAuthService().logout();
            this.navCtrl.setRoot("LoginPage");
          }
        }
      ]
    });
    alert.present();


  }


  presentPopover(event) {
    let popover = this.popoverCtrl.create("UserInfoTabPopoverPage", { mainPage: this });
    popover.present({
      ev: event
    });
  }


  fijarIP() {
    global.fun(this.ip);
  }


}
