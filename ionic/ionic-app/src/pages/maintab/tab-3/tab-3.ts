import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ServiceManagerProvider } from '../../../providers/services/service-manager';

/**
 * Generated class for the Tab_3Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-tab-3',
  templateUrl: 'tab-3.html',
})
export class Tab_3Page {

  private routes = [];
  private index = 0;
  private count = 6;

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider) {
    console.log(this.serviceManagerProvider.getAuthService().getUserInfo());
    this.getInfo();
  }


  doInfinite(): Promise<any> {
    return new Promise((resolve) => {
      this.serviceManagerProvider.getRouteService().getAll(this.index, this.count).subscribe(
        data => {
          let datajson = data.json();
          if (datajson.length != 0) {
            this.index = this.index + this.count;
          }
          for (let d in datajson) {
            this.routes.push(datajson[d]);
          }
          resolve();
        },
        err => console.log(err)
      );
    });
  }

  

  getInfo() {
    this.serviceManagerProvider.getRouteService().getAll(this.index, this.count).subscribe(
      data => {
        this.index = this.index + this.count;
        let datajson = data.json();
        for (let d in datajson) {
          this.routes.push(datajson[d]);
        }
      },
      err => console.log(err)
    );
  }

  goToPanel(route) {
    this.navCtrl.push("MainPanelPage", {
      param1: route
    });
  }
}
