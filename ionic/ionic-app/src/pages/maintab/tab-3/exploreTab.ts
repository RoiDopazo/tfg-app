import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { ServiceManagerProvider } from '../../../providers/services/service-manager';
import moment from "moment";

/**
 * Generated class for the Tab_3Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-exploretab',
  templateUrl: 'exploreTab.html',
})
export class ExploreTabPage {

  private routes = [];
  private index = 0;
  private count = 6;

  private filterCity = "";
  private filterState = "";
  private filterNumDays = "";
  private filterMaxDistance = "";
  private filterMaxDuration = "";
  private numFilters = false;

  constructor(public navCtrl: NavController, public navParams: NavParams, 
    private serviceManagerProvider: ServiceManagerProvider, 
    private alertCtrl: AlertController) {
      
    this.getInfo();
  }


  doInfinite(): Promise<any> {
    return new Promise((resolve) => {
      this.serviceManagerProvider.getRouteService().explore(this.filterCity, this.filterState, this.filterNumDays, this.filterMaxDistance, this.filterMaxDuration, this.index, this.count).subscribe(
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
        err => this.serviceManagerProvider.handleError(err)
      );
    });
  }

  

  getInfo() {
    this.serviceManagerProvider.getRouteService().explore(this.filterCity, this.filterState, this.filterNumDays, this.filterMaxDistance, this.filterMaxDuration, this.index, this.count).subscribe(
      data => {
        let datajson = data.json();
        if (datajson.length > 0) {
          for (let d in datajson) {
            this.routes.push(datajson[d]);
          }
          this.index = this.index + this.count;
        }
      },
      err => {
        this.serviceManagerProvider.handleError(err);   
      }
    );
  }


  openFilters() {
    let alert = this.alertCtrl.create({
      title: 'Filtrar',
      inputs: [
        {
          type: "text",
          name: 'city',
          placeholder: 'Ciudad',
          value: this.filterCity,
        },
        {
          type: "text",
          name: 'state',
          placeholder: 'Estado (pendiente, en curso, completada)',
          value: this.filterState,
        },
        {
          type: "number",
          name: 'numDays',
          placeholder: 'Número de días',
          value: this.filterNumDays
        },
        {
          type: "number",
          name: 'maxDistance',
          placeholder: 'Distancia máxima (metros)',
          value: this.filterMaxDistance
        },
        {
          type: "number",
          name: 'maxDuration',
          placeholder: 'Duración máxima (horas)',
          value: this.filterMaxDuration
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
          text: 'Aplicar',
          handler: data => {
            this.filterCity = data.city;
            this.filterMaxDistance = data.maxDistance;
            if (data.maxDuration != "") {
              let maxDurInt = data.maxDuration * 3600;
              this.filterMaxDuration = maxDurInt.toString();
            } else {
              this.filterMaxDuration = data.maxDuration;
            }
            this.filterNumDays = data.numDays;
            this.filterState = data.state;
            this.resetFilters();
          }
        }
      ]
    });
    alert.present();
  }

  removeFilter(filter) {
    if (filter == "filterCity") {
      this.filterCity = "";
    } else if (filter == "filterState") {
      this.filterState = "";
    } else if (filter == "filterNumDays") {
      this.filterNumDays = "";
    } else if (filter == "filterMaxDistance") {
      this.filterMaxDistance = "";
    } else if (filter == "filterMaxDuration") {
      this.filterMaxDuration = "";
    }
    this.resetFilters();
  }

  goToPanel(route) {
    this.navCtrl.push("MainPanelPage", {
      param1: route.id
    });
  }

  resetFilters() {
    this.numFilters = false;
    if ((this.filterCity != "") || (this.filterMaxDistance != "") || (this.filterMaxDuration != "") || (this.filterNumDays != "") || (this.filterState != "")) {
      this.numFilters = true;
    }
    this.index = 0;
    this.count = 6;
    this.routes = [];
    this.getInfo();
  }

  openRouteInfo(id) {
    this.navCtrl.push("MainPanelPage", {
      param1: id
    });
  }

  getDateAsString(date) {
    return moment(date).utc().format("DD-MMM-YYYY");
  }
}
