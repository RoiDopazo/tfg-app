import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { CalendarModal, CalendarModalOptions } from "ion2-calendar";
import moment from "moment";
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import { LocationTrackerProvider } from '../../providers/location-tracker/location-tracker';
import { AuthServiceProvider } from '../../providers/auth-service/auth-service';

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
  private user;

  constructor(public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private serviceManagerProvider: ServiceManagerProvider, private locationTrackerProvider: LocationTrackerProvider, private auth: AuthServiceProvider) {
    this.routeid = navParams.get('param1');
    this.user = this.serviceManagerProvider.getAuthService().getUserInfo();
    this.serviceManagerProvider.getRouteService().getById(this.routeid).subscribe(
      data => {
        this.route = data.json();
      },
      err => {
        this.serviceManagerProvider.handleError(err);
      }
    );
    this.hideTabbar();
  }


  ionViewDidEnter() {
    if(this.route != null) {
      this.serviceManagerProvider.getRouteService().getById(this.route.id).subscribe(
        data => {
          this.route = data.json();
        },
        err => this.serviceManagerProvider.handleError(err)
      );
    }
  }

  getDateAsString(date) {
    return moment(date).format("DD-MMM-YYYY");
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
    let datems = new Date().getTime() + 86400000;
    let date = new Date(datems);
    const options: CalendarModalOptions = {
      pickMode: 'range',
      title: 'Fechas',
      color: 'danger',
      weekdays: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      weekStart: 1,
      closeLabel: 'Cancelar',
      doneLabel: 'Hecho',
      from: date
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
            this.route.startDate = moment(date1).valueOf();
            this.route.endDate = moment(date2).valueOf();
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

    if (this.route.owner == this.user.id) {
      let now = moment().format("DD/MM/YYYY");
      let isInDay = false;
      let day = undefined;
      for (let x = 0; x < this.route.numDays; x++) {
        let routeNow = moment(this.route.startDate + x * 86400000).format("DD/MM/YYYY");
        if (routeNow == now) {
          isInDay = true;
          day = x + 1;
        }
      }

      if (this.route.state == "COMPLETED") {
        this.serviceManagerProvider.showError("La ruta ya ha finalizado.", "Consulte los datos obtenidos en la pestaña Mapa");
      } else if (isInDay) {
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
        this.serviceManagerProvider.showError("La ruta aún no ha comenzado.", "Los dats reales de la ruta solo se podrán guardar cuando llegue el día de comienzo de la ruta");
      }
    } else {
      this.serviceManagerProvider.showError("No eres propietario de la ruta", "Solo los propietarios de cada ruta podrán guardar datos en tiempo real sobre la misma.");
    }
    
  }
}
