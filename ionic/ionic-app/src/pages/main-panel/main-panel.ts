import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { CalendarModal, CalendarModalOptions } from "ion2-calendar";
import moment from "moment";
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import { LocationTrackerProvider } from '../../providers/location-tracker/location-tracker';

/**
 * Generated class for the MainPanelPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-main-panel',
  templateUrl: 'main-panel.html',
})
export class MainPanelPage {

  private route;
  private routeid;
  // Barra de tabs
  private tabbar;

  constructor(public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private serviceManagerProvider: ServiceManagerProvider, private locationTrackerProvider: LocationTrackerProvider) {
    this.routeid = navParams.get('param1');
    this.serviceManagerProvider.getRouteService().getById(this.routeid).subscribe(
      data => {
        this.route = data.json();
      },
      err => {

      }
    );
    this.hideTabbar();
  }


  ionViewDidEnter() {
    this.serviceManagerProvider.getRouteService().getById(this.route.id).subscribe(
      data => {
        this.route = data.json();
      },
      err => this.serviceManagerProvider.handleError(err)
    );
  }

  getDateAsString(date) {
    return moment(date).utc().format("DD-MMM-YYYY");
  }

  hideTabbar() {
    this.tabbar = document.querySelectorAll(".tabbar");
    if (this.tabbar != null) {
      Object.keys(this.tabbar).map((key) => {
        this.tabbar[key].style.display = 'none';
      });
    }
  }

  ngOnDestroy() {
    if (this.tabbar != null) {
      Object.keys(this.tabbar).map((key) => {
        this.tabbar[key].style.display = '';
      });
    }
  }

  openCalendar() {
    const options: CalendarModalOptions = {
      pickMode: 'range',
      title: 'Fechas',
      color: 'danger',
      weekdays: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      weekStart: 1,
      closeLabel: 'Cancelar',
      doneLabel: 'Hecho'

    };

    let myCalendar = this.modalCtrl.create(CalendarModal, {
      options: options
    });

    myCalendar.present();

    myCalendar.onDidDismiss(date => {
      if (date) {
        let date1 = moment(date.from.string, "YYYY/MM/DD");
        let date2 = moment(date.to.string, "YYYY/MM/DD");

        let days = date2.diff(date1, "days") + 1;

        this.serviceManagerProvider.getRouteService().setNumDays(this.route, days).subscribe(
          data => {
            this.route.startDate = date.from.time;
            this.route.endDate = date.to.time;
            this.serviceManagerProvider.getRouteService().update(this.route).subscribe(
              data => {
                this.route = data.json()
              },
              err => {
                this.serviceManagerProvider.handleError(err);
              }
            );
          },
          err => this.serviceManagerProvider.handleError(err)
        );
      }
    });
  }

  openMainSearch() {
    this.navCtrl.push("MainSearchPage", {
      param1: this.route
    });
  }

  openMap() {
    this.navCtrl.push("MapPage", {
      route: this.route
    })
  }

  openAbout() {
    this.navCtrl.push("MainPanelAboutPage", {
      route: this.route
    })
  }

  openEvents() {
    this.navCtrl.push("EventsPage", {
      route: this.route
    });
  }


  allowGeoLoc() {

    let now = moment().utc().format("DD/MM/YYYY");
    let isInDay = false;
    let day = undefined;
    for (let x = 0; x < this.route.numDays; x++) {
      let routeNow = moment(this.route.startDate + x * 86400000).utc().format("DD/MM/YYYY");
      if (routeNow == now) {
        isInDay = true;
        day = x + 1;
      }
    }

    if (isInDay) {
      let img = document.getElementById('geo-track-img');
      if (this.locationTrackerProvider.getStatus()) {
        img.classList.remove("icono_col_geo");
        img.classList.add("icono_col");
        this.locationTrackerProvider.stopTracking();

      } else {
        img.classList.remove("icono_col");
        img.classList.add("icono_col_geo");
        this.locationTrackerProvider.startTracking(this.route.id, day);
      }
    } else {
      this.serviceManagerProvider.showError("La ruta aún no ha comenzado.", "Los tiempos reales de la ruta solo se podrán guardar cuando llegue el día de comienzo de la ruta");
    }
  }
}
