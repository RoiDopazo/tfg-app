import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
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
  selector: 'page-tab-4',
  templateUrl: 'tab-4.html',
})
export class Tab_4Page {

  private user;
  private state = "pending";
  private routes = [];

  private ip = global.SERVER_IP;

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider, private alertCtrl: AlertController) {
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

  getDateAsString(date) {
    return moment(date).utc().format("DD-MMM-YYYY");
  }

  change() {
    this.serviceManagerProvider.getRouteService().getOwnRoutes(this.state).subscribe(
      data => {
        this.routes = [];
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
  openRouteInfo(id) {
      this.navCtrl.push("MainPanelPage", {
        param1: id
      });
    }


    editUserData() {
      this.serviceManagerProvider.getUserServiceAuth().getUserInfo().subscribe(
        data => {
          let userinfo = data.json();
          console.log(userinfo);
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
                placeholder: 'Password',
                type: 'password'
              }
            ],
            buttons: [
              {
                text: 'Cancel',
                role: 'cancel',
                handler: data => {
                  console.log('Cancel clicked');
                }
              },
              {
                text: 'Modificar',
                handler: data => {
                  if (data.password == "") {
                    this.serviceManagerProvider.presentNativeToast("Password no puede ser vacía");
                    this.editUserData();
                  }
                  console.log(data);
                  this.serviceManagerProvider.getUserServiceAuth().updateUser(userinfo.id, data).subscribe(
                    data => {
                      this.serviceManagerProvider.presentNativeToast("Datos modificados correctamente");
                    },
                    err => {
                      this.serviceManagerProvider.handleError(err);
                    }
                  );
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
  

  openMore($event) {
    let alert = this.alertCtrl.create({
      title: 'Logout',
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


  fijarIP() {
    global.fun(this.ip);
  }


}
