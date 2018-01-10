import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';
import moment from "moment";

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

  constructor(public navCtrl: NavController, public navParams: NavParams, private serviceManager: ServiceManagerProvider) {
    this.select = "onTrip";
    this.route = this.navParams.get("route");
    console.log(this.route);
    this.getInitEventsIn();
    this.getInitEventsOut();
    
  }

  getInitEventsIn() {
    this.serviceManager.getEventService().getAllByDateRange(this.route.startDate-86400000, this.route.endDate, this.indexIn, this.count).subscribe(
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
    this.serviceManager.getEventService().getAllByDateOver(this.route.endDate, this.indexOut, this.count).subscribe(
      data => {
        this.indexOut = this.indexOut + this.count;
        let datajson = data.json();
        for (let d in datajson) {
          this.eventsOut.push(datajson[d]);
        }
        console.log(this.eventsOut);
      },
      err => {
        console.log(err)
      }
    );
  }

  getNumberOfTheDate(miliseconds) {
    return moment.utc(miliseconds).format("DD");
  }
  
  getMonthOfTheDate(miliseconds) {
    return moment.utc(miliseconds).format("MMM").toLowerCase();
  }

  getYearOfTheDate(miliseconds){
    return moment.utc(miliseconds).format("YYYY");
  }

  getHourAsString(hour) {
     return moment.utc(hour).format("HH:mm");
  }


  doInfiniteIn(): Promise<any> {
    return new Promise((resolve) => {
      this.serviceManager.getEventService().getAllByDateRange(this.route.startDate-86400000, this.route.endDate, this.indexIn, this.count).subscribe(
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

  doInfinitOut(): Promise<any> {
    return new Promise((resolve) => {
      this.serviceManager.getEventService().getAllByDateOver(this.route.endDate, this.indexOut, this.count).subscribe(
        data => {
          let datajson = data.json();
          if (datajson.length != 0) {
            this.indexOut = this.indexOut + this.count;
          }
          for (let d in datajson) {
            this.eventsIn.push(datajson[d]);
          }
          resolve();
        },
        err => console.log(err)
      );
    });
  }

  checkEventInRoute(eventPlace, event) {
    let day = (event.date - this.route.startDate) / 86400000;
    for (let stay of this.route.days[day].stays) {
      if (stay.eventPlace) {
        if (stay.eventPlace.id == eventPlace.id) {
          return true;
        }
      }
    }
    return false;
  }


  addToRoute (eventPlace, event) {
    let day = (event.date - this.route.startDate) / 86400000;
    day += 1;
    if (!this.checkEventInRoute(eventPlace, event)) {
      this.serviceManager.getRouteService().stay_createByEvent(this.route.id, day, eventPlace).subscribe(
        data => {
          this.serviceManager.getRouteService().getById(this.route.id).subscribe(
            data => {
              this.route = data.json();
            },
            err => console.log(err)
          );
        },
        err => console.log(err)
      );
    } else {
      //delete
    }
    
  }


  changeEventPlaceShow(event) {
    if (event.eventPlaces.length != 0) {
      event.eventPlaceShow = !event.eventPlaceShow;
    }
  }
}
