import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { CalendarModal, CalendarModalOptions } from "ion2-calendar";
import moment from "moment";
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

  private route = {
    "id": 100,
    "photo": "https://www.deportetotalfm.com/wp-content/uploads/2016/10/fondos-abstractos-para-paginas-web-para-fondo-celular-en-hd-12.jpg",
    "state": "PENDIENTE",
    "num_places": 0,
    "city": "Madrid",
    "country": "España",
    "distance": 0,
    "time": 0,
    "num_days": 0,
    "creation_date": "06/10/2017",
    "start_date": undefined,
    "end_date": undefined
  };

  constructor(public navCtrl: NavController, public navParams: NavParams, private modalCtrl: ModalController) {

    //this.route = navParams.get('param1'); 
    //this.photo = navParams.get('param4');
    console.log(this.route);
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
      console.log(date);
      if (date) {
        let date1 = moment(date.from.string, "YYYY/MM/DD");
        let date2 =  moment(date.to.string, "YYYY/MM/DD");
        this.route.start_date = date1.format("DD-MMM-YYYY");
        this.route.end_date = date2.format("DD-MMM-YYYY");
        let days = date2.diff(date1, "days");
        this.route.num_days = days + 1;
      }
    });
  }

  openMainSearch() {
    this.navCtrl.push("MainSearchPage");
  }

}
