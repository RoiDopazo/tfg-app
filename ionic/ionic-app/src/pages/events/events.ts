import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController, ModalController  } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import moment from "moment";
import { Toast } from '@ionic-native/toast';

/**
 * Generated class for the EventsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-events',
  templateUrl: 'events.html',
})
export class EventsPage {

  private select;
  private route;
  private eventsIn = [];
  private eventsOut = [];
  private indexIn = 0;
  private indexOut = 0;
  private count = 10;

  constructor(public navCtrl: NavController, public navParams: NavParams, public modalCtrl: ModalController, private toast: Toast, private alertCtrl: AlertController, private serviceManager: ServiceManagerProvider) {
    this.select = "onTrip";
    this.route = this.navParams.get("route");
    if (this.route.startDate != null) {
      this.getInitEventsIn();
    }
    this.getInitEventsOut();
  }

  getInitEventsIn() {
    this.serviceManager.getEventService().getAllByDateRange(this.route.city, this.route.startDate, this.route.endDate, this.indexIn, this.count).subscribe(
      data => {
        this.indexIn = this.indexIn + this.count;
        let datajson = data.json();
        for (let d in datajson) {
          datajson[d]["eventPlaceShow"] = false;
          this.eventsIn.push(datajson[d]);
        }
        console.log(this.eventsIn);
      },
      err => {
        console.log(err)
      }
    );
  }

  getInitEventsOut() {
    let date = new Date();
    let lastDay = this.route.endDate == null ? date.getTime() : this.route.endDate;
    this.serviceManager.getEventService().getAllByDateOver(this.route.city, lastDay, this.indexOut, this.count).subscribe(
      data => {
        this.indexOut = this.indexOut + this.count;
        let datajson = data.json();
        for (let d in datajson) {
          this.eventsOut.push(datajson[d]);
        }
      },
      err => {
        console.log(err)
      }
    );
  }

  getNumberOfTheDate(miliseconds) {
    return moment(miliseconds).format("DD");
  }
  
  getMonthOfTheDate(miliseconds) {
    return moment(miliseconds).format("MMM").toLowerCase();
  }

  getYearOfTheDate(miliseconds){
    return moment(miliseconds).format("YYYY");
  }

  getHourAsString(hour) {
     return moment(hour).format("HH:mm");
  }


  doInfiniteIn(): Promise<any> {
    
    return new Promise((resolve) => {
      this.serviceManager.getEventService().getAllByDateRange(this.route.city, this.route.startDate, this.route.endDate, this.indexIn, this.count).subscribe(
        data => {
          let datajson = data.json();
          if (datajson.length != 0) {
            this.indexIn = this.indexIn + this.count;
          }
          for (let d in datajson) {
            datajson[d]["eventPlaceShow"] = false;
            this.eventsIn.push(datajson[d]);
          }
          resolve();
        },
        err => console.log(err)
      );
    });
  }

  doInfiniteOut(): Promise<any> {
    let date = new Date();
    let lastDay = this.route.endDate == null ? date.getTime() : this.route.endDate;
    return new Promise((resolve) => {
      this.serviceManager.getEventService().getAllByDateOver(this.route.city, lastDay, this.indexOut, this.count).subscribe(
        data => {
          let datajson = data.json();
          if (datajson.length != 0) {
            this.indexOut = this.indexOut + this.count;
          }
          for (let d in datajson) {
            this.eventsOut.push(datajson[d]);
          }
          resolve();
        },
        err => console.log(err)
      );
    });
  }

  checkEventInRoute(eventPlace, event) {
    var eventDate = moment(event.date);
    var routeDate = moment(this.route.startDate);
    let day = eventDate.diff(routeDate, "days");
    for (let stay of this.route.days[day].stays) {
      if (stay.eventPlace) {
        if (stay.eventPlace.id == eventPlace.id) {
          return stay.id;
        }
      }
    }
    return null;
  }


  addToRoute (eventPlace, event) {
    var eventDate = moment(event.date);
    var routeDate = moment(this.route.startDate);
    let day = eventDate.diff(routeDate, "days") + 1;
    let stayId = this.checkEventInRoute(eventPlace, event);
    if (stayId == null) {
      this.persentAlertToAdd(this.route.id, day, eventPlace);
    } else {
      this.presentAlertToDelete(stayId);
    }
  }

  changeEventPlaceShow(event) {
    if (event.eventPlaces.length != 0) {
      event.eventPlaceShow = !event.eventPlaceShow;
    }
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
  
  persentAlertToAdd(routeId, day, eventPlace) {
    let alert = this.alertCtrl.create({
      title: 'Añadir evento a ruta',
      message: 'Está usted seguro de querer añadir este evento a su ruta?',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Aceptar',
          handler: () => {
            console.log('Aceptar clicked');
            this.serviceManager.getRouteService().stay_createByEvent(this.route.id, day, eventPlace).subscribe(
              data => {
                this.presentToast("Evento añadido correctamente a la ruta");
                this.serviceManager.getRouteService().getById(this.route.id).subscribe(
                  data => {
                    this.route = data.json();
                  },
                  err => console.log(err)
                );
              },
              err => console.log(err)
            );
          }
        }
      ]
    });
    alert.present();
  }

  presentAlertToDelete(stayId) {
    let alert = this.alertCtrl.create({
      title: 'Eliminar evento asignado',
      message: 'Está usted seguro de querer eliminar este evento asignado en su ruta?',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Aceptar',
          handler: () => {
            console.log('Aceptar clicked');
            this.serviceManager.getRouteService().stay_delete(stayId).subscribe(
              data => {
                this.presentToast("Evento eliminado correctamente de la ruta");
                this.serviceManager.getRouteService().getById(this.route.id).subscribe(
                  data => {
                    this.route = data.json();
                  },
                  err => console.log(err)
                );
              },
              err => {
                this.presentToast("No se pudo eliminar el evento de la ruta");
              }
            );
          }
        }
      ]
    });
    alert.present();
  }


  showLocationMap(eventPlace, event) {
    let day = (event.date - this.route.startDate) / 86400000;
    day += 1;
    console.log(day);
    this.navCtrl.push("MapPage", {
      route: this.route,
      day: day,
      ePlace: eventPlace
    });
  }
}
