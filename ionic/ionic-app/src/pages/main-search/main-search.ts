import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ActionSheetController, reorderArray, Events, PopoverController, LoadingController } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import moment from "moment";

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

  constructor(public loadingCtrl: LoadingController, public popoverCtrl: PopoverController, public events: Events, public navCtrl: NavController, public navParams: NavParams, private serviceManagerProvider: ServiceManagerProvider, private actionSheetCtrl: ActionSheetController) {

    this.route = navParams.get('param1');
    this.initDayVariables();
    events.subscribe('place:mod', (idRoute) => {
      this.serviceManagerProvider.getRouteService().getById(idRoute).subscribe(
        data => {
          this.route = data.json();
        },
        err => console.log(err)
      );
    });
  }

  initDayVariables() {
      this.current_day = 2;
      this.current_day_less = this.current_day - 1;
      this.current_day_plus = this.current_day + 1;
      this.select_day = this.current_day_less;
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

  convertDateToMs(date) {
    let ms = moment.duration(date, 'milliseconds');
    return ms.asMilliseconds();
  }

  getArrivalTimeInMs(startTime, places, index) {
    let sum = parseFloat(startTime);
    for (let i = 0; i < index; i++) {
      sum = sum + + parseFloat(places[i].time) + parseFloat(places[i].travelTime);
    }
    return sum;
  }

  getArrivalTime(startTime, places, index) {
    let sum = parseFloat(startTime);
    for (let i = 0; i < index; i++) {
      sum = sum + + parseFloat(places[i].time) + parseFloat(places[i].travelTime);
    }
    return moment.utc(sum).format("HH:mm");
  }

  getDepartureTime(startTime, places, index, time) {
    let time2 = moment.duration(time).asMilliseconds();
    let sum = this.getArrivalTimeInMs(startTime, places, index) + time2;
    return moment.utc(sum).format("HH:mm");
  }

  getInitialDepartureTime(startTime, time) {
    let time2 = moment.duration(time).asMilliseconds();
    let sum = parseFloat(startTime) + time2;
    return moment.utc(sum).format("HH:mm");
  }

  setPlaceTime(currentDay, place, selectedTime) {
    this.route.days[currentDay - 1].places[place].time = this.convertDateToMs(selectedTime);
  }


  changeTravelMode(travelMode, current_day, index) {
    switch (travelMode) {
      case "WALKING":
        this.route.days[current_day].places[index].travelMode = "DRIVING";
        break;
      case "DRIVING":
        this.route.days[current_day].places[index].travelMode = "BICYCLING";
        break;
      case "BICYCLING":
        this.route.days[current_day].places[index].travelMode = "WALKING";
        break;
    }
    this.presentLoading();
    this.serviceManagerProvider.getGoogleService().getTravelInfo(this.route.days[current_day].places[index].place.lat, this.route.days[current_day].places[index].place.lng, this.route.days[current_day].places[index+1].place.lat, this.route.days[current_day].places[index+1].place.lng, this.route.days[current_day].places[index].travelMode).subscribe(
      data => {
        this.route.days[current_day].places[index].travelDistance = data.json()[0];
        this.route.days[current_day].places[index].travelTime = data.json()[1];
        this.loading.dismiss();
        this.serviceManagerProvider.getRouteService().day_place_update_b(this.route.id, this.route.days[current_day].idDay, this.route.days[current_day].places).subscribe(
          data => {
          },
          err => console.log(err)
        );
      },
      err => console.log(err)
    );
  }


  

  oneMoreDay() {
    let element: HTMLElement = document.getElementById("select_button" + (this.select_day - 1));
    console.log(element);
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
  }

  oneDayLess() {
    let value = (parseInt(this.select_day) + 1);
    let element: HTMLElement = document.getElementById("select_button" + value);
    console.log(element);
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
    this.route.days[current_day - 1].places = reorderArray(this.route.days[current_day - 1].places, indexes);
    let index = 1;
    let dayPlaceList = [];
    for (let place of this.route.days[current_day - 1].places) {
      place.order = index;
      index++;
      dayPlaceList.push(place);
    }

    this.presentLoading();
    this.serviceManagerProvider.getGoogleService().getTravelInfoBatch(dayPlaceList).subscribe(
      data => {
        this.route.days[current_day - 1].places = data.json();
        this.serviceManagerProvider.getRouteService().day_place_update_b(this.route.id, this.route.days[current_day - 1].idDay, this.route.days[current_day - 1].places).subscribe(
          data => {
            this.loading.dismiss();
          },
          err => console.log(err)
        );
      },
      err => console.log(err)
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
            console.log('Añadir Evento');
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

  openMap() {
    this.navCtrl.push("MapPage", {
      route: this.route,
      numDay: this.select_day
    });
  }
}
