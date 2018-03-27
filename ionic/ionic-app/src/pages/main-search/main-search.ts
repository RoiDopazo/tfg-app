import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ActionSheetController, reorderArray, Events, PopoverController, LoadingController } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import moment from "moment";
import { Toast } from '@ionic-native/toast';

/**
 * Generated class for the MainSearchPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-main-search',
  templateUrl: 'main-search.html',
})
export class MainSearchPage {

  private current_day_less;
  private current_day_plus;
  private current_day;
  private select_day;
  private route;
  private startTime;
  private editing: boolean = false;
  private editButton: string = "attach";

  private loading;
  private selectedTime;

  constructor(public loadingCtrl: LoadingController, public popoverCtrl: PopoverController, private toast: Toast,  public events: Events, public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider, private actionSheetCtrl: ActionSheetController) {

    this.route = navParams.get('param1');
    console.log(this.route);
    this.initDayVariables();
    events.subscribe('place:mod', (idRoute) => {
      this.serviceManagerProvider.getRouteService().getById(idRoute).subscribe(
        data => {
          this.route = data.json();
        },
        err => this.serviceManagerProvider.handleError(err)
      );
    });
  }

  ionViewDidLoad() {
    let element: HTMLElement = document.getElementById("select_button1");
    element.classList.add("segment-activated", "activated");
  }
  
  
  removeActive() {
    let element: HTMLElement = document.getElementById("select_button1");
    if (element != null) {
      element.classList.remove("segment-activated", "activated");
    }
  }

  initDayVariables() {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
  }

  getCurrentDate(select_day) {
    moment.locale('es');
    let oneDayInMs = 86400000;
    return moment(this.route.startDate + oneDayInMs * select_day).utc().format("DD [de] MMMM [de] YYYY");;
  }

  toggleEdit() {
    this.editing = !this.editing;
    if (this.editing) {
      this.editButton = 'checkmark';
    } else {
      this.editButton = 'attach';
    }
  }

  convertMsToDate(miliseconds) {
    let date = moment.utc(miliseconds).format("HH:mm");
    return date;
  }

  convertMsToString(miliseconds) {
    let date = moment.duration(miliseconds, 'milliseconds');
    return date.hours() + "h " + date.minutes() + "m";
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
    return distance + " metros";
  }

  convertDateToMs(date) {
    let ms = moment.duration(date, 'milliseconds');
    return ms.asMilliseconds();
  }

  getArrivalTimeInMs(startTime, stays, index) {
    let sum = parseFloat(startTime);
    for (let i = 0; i < index; i++) {
      sum = sum + + parseFloat(stays[i].time) + parseFloat(stays[i].travelTime);
    }
    return sum;
  }

  getArrivalTime(startTime, stays, index) {
    let sum = parseFloat(startTime);
    for (let i = 0; i < index; i++) {
      sum = sum + + parseFloat(stays[i].time) + parseFloat(stays[i].travelTime);
    }
    return moment.utc(sum).format("HH:mm");
  }

  getDepartureTimeInMs(startTime, stays, index, time) {
    let time2 = moment.duration(time).asMilliseconds();
    let sum = this.getArrivalTimeInMs(startTime, stays, index) + time2;
    return sum;
  }

  getDepartureTime(startTime, stays, index, time) {
    let time2 = moment.duration(time).asMilliseconds();
    let sum = this.getArrivalTimeInMs(startTime, stays, index) + time2;
    return moment.utc(sum).format("HH:mm");
  }

  getInitialDepartureTime(startTime, time) {
    let time2 = moment.duration(time).asMilliseconds();
    let sum = parseFloat(startTime) + time2;
    return moment.utc(sum).format("HH:mm");
  }

  setStayTime(currentDay, stay, selectedTime) {
    this.route.days[currentDay - 1].stays[stay].time = this.convertDateToMs(selectedTime);
    
  }


  changeTravelMode(travelMode, current_day, index) {
    switch (travelMode) {
      case "WALKING":
        this.route.days[current_day].stays[index].travelMode = "DRIVING";
        break;
      case "DRIVING":
        this.route.days[current_day].stays[index].travelMode = "BICYCLING";
        break;
      case "BICYCLING":
        this.route.days[current_day].stays[index].travelMode = "WALKING";
        break;
    }
    this.presentLoading();

    let siteBefore = this.route.days[current_day].stays[index].place ? this.route.days[current_day].stays[index].place : this.route.days[current_day].stays[index].eventPlace;
    let siteAfter = this.route.days[current_day].stays[index+1].place ? this.route.days[current_day].stays[index+1].place : this.route.days[current_day].stays[index+1].eventPlace;

    this.serviceManagerProvider.getGoogleService().getTravelInfo(siteBefore.lat, siteBefore.lng, siteAfter.lat, siteAfter.lng, this.route.days[current_day].stays[index].travelMode).subscribe(
      data => {
        let travelDistanceB4 = this.route.days[current_day].stays[index].travelDistance;
        let travelTimeB4 = this.route.days[current_day].stays[index].travelTime;
        this.route.days[current_day].stays[index].travelDistance = data.json()[0];
        this.route.days[current_day].stays[index].travelTime = data.json()[1];
        this.serviceManagerProvider.getRouteService().stay_update_batch(this.route.days[current_day].stays).subscribe(
          data => {
            this.loading.dismiss();
          },
          err => {
            this.route.days[current_day].stays[index].travelDistance = travelDistanceB4;
            this.route.days[current_day].stays[index].travelTime = travelTimeB4;
            this.route.days[current_day].stays[index].travelMode = travelMode;
            this.loading.dismiss();
            this.serviceManagerProvider.handleError(err);
          }
        );
      },
      err => this.serviceManagerProvider.handleError(err)
    );
    
  }


  

  oneMoreDay() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day - 1));
    if (!(this.route.numDays == this.current_day_plus)) {
      this.current_day_less = this.current_day_less + 1;
      this.current_day = this.current_day + 1;
      this.current_day_plus = this.current_day_plus + 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.add("segment-activated", "activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated", "activated");
      }
    }
      this.removeActive();
    
    
  }

  oneDayLess() {
    let value = (parseInt(this.select_day) + 1);
    let element: HTMLElement = document.getElementById("select_button" + value);
    if (this.current_day_less > 1) {
      this.current_day_less = this.current_day_less - 1;
      this.current_day = this.current_day - 1;
      this.current_day_plus = this.current_day_plus - 1;
      this.select_day = this.current_day;
      if (element != null) {
        element.classList.add("segment-activated", "activated");
      }
    } else {
      if (element != null) {
        element.classList.remove("segment-activated", "activated");
      }
    }
  }

  reorderItems(current_day, indexes) {
    this.route.days[current_day - 1].stays = reorderArray(this.route.days[current_day - 1].stays, indexes);
    let index = 1;
    let daystayList = [];
    for (let stay of this.route.days[current_day - 1].stays) {
      stay.order = index;
      index++;
      daystayList.push(stay);
    }

    this.presentLoading();
    this.serviceManagerProvider.getGoogleService().getTravelInfoBatch(daystayList).subscribe(
      data => {
        this.route.days[current_day - 1].stays = data.json();
        this.serviceManagerProvider.getRouteService().stay_update_batch(this.route.days[current_day - 1].stays).subscribe(
          data => {
            this.loading.dismiss();
          },
          err => this.serviceManagerProvider.handleError(err)
        );
      },
      err => this.serviceManagerProvider.handleError(err)
    );
  }

  showActionsAdd() {
    let actionSheet = this.actionSheetCtrl.create({
      buttons: [
        {
          text: 'Lugar',
          handler: () => {
            this.navCtrl.push("MainPlacesPage", {
              param1: this.route
            });
          }
        }, {
          text: 'Evento',
          handler: () => {
            
              this.navCtrl.push("EventsPage", {
                route: this.route
              });
            
          }
        }, {
          text: 'Alojamiento',
          handler: () => {
            console.log('Añadir Alojamiento');
          }
        }, {
          text: 'Hora de Comienzo',
          handler: () => {

            console.log('Añadir Hora Comienzo');
          }
        }
      ]
    });
    actionSheet.present();
  }


  deleteStay(idStay, selectDay) {
    this.presentLoading();
    this.serviceManagerProvider.getRouteService().stay_delete(idStay).subscribe(
      data => {
        let staysNotRemoved = this.route.days[selectDay].stays.filter(function(value) {
          return value.id != idStay;
        });
        this.route.days[selectDay].stays = staysNotRemoved;
        this.presentToast("Visita eliminada correctamente");
        this.loading.dismiss();
      },
      err => {
        this.presentToast("No se pudo eliminar correctamente la visita. Inténtelo de nuevo o más tarde");
        this.loading.dismiss();
        this.serviceManagerProvider.handleError(err);
      }
    );
  }


  presentPopover(event) {
    let popover = this.popoverCtrl.create("MainSearchPopoverPage", { mainPage: this });
    popover.present({
      ev: event
    });
  }


  presentLoading() {
    this.loading = this.loadingCtrl.create({
      spinner: 'bubbles',
      content: 'Please wait...'
    });

    this.loading.present();
  }

  presentToast(text:string) {
    this.toast.showLongBottom(text).subscribe(
      toast => {
        console.log(toast);
      },
      err => {
        console.log(err);
      }
    );;
  }

  openMap() {
    this.navCtrl.push("MapPage", {
      route: this.route,
      numDay: this.select_day,
      dataDays: {
        current_day_less: this.current_day_less,
        current_day_plus: this.current_day_plus,
        current_day: this.current_day,
        select_day: this.select_day  
      }
    });
  }
}
