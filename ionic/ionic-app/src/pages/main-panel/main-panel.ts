import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { CalendarModal, CalendarModalOptions } from "ion2-calendar";
import moment from "moment";
import { ServiceManagerProvider } from '../../providers/services/service-manager';

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
  private startDateString;
  private endDateString;
  // Barra de tabs
  private tabbar;

  constructor(public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController, private serviceManagerProvider: ServiceManagerProvider) {

    this.route = navParams.get('param1');
    console.log(this.route);
    if (this.route.startDate) {
      this.startDateString = moment(this.route.startDate).format("DD-MMM-YYYY");
      this.endDateString = moment(this.route.endDate).format("DD-MMM-YYYY");
    }
    this.hideTabbar();
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

        this.route.startDate = moment(date1).valueOf();
        this.route.endDate = moment(date2).valueOf();

        this.startDateString = moment(this.route.startDate).format("DD-MMM-YYYY");
        this.endDateString = moment(this.route.endDate).format("DD-MMM-YYYY");

        let days = date2.diff(date1, "days") + 1;

        this.serviceManagerProvider.getRouteService().setNumDays(this.route, days).subscribe(
          data => {
            this.serviceManagerProvider.getRouteService().update(this.route).subscribe(
              data => {
                this.route = data.json()
                console.log("ruta updateada con los días");
                console.log(this.route);
              }
            );
          },
          err => console.log(err)
        );
      }
    });
  }

  openMainSearch() {
    this.navCtrl.push("MainSearchPage", {
      param1: this.route
    });
  }

}
