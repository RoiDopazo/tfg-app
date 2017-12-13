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
  private startDate;
  private endDate;
  private eventsIn = [];
  private eventsOut = [];
  private indexIn = 0;
  private indexOut = 0;
  private count = 10;

  constructor(public navCtrl: NavController, public navParams: NavParams, private service: ServiceManagerProvider) {
    this.select = "onTrip";
    this.startDate = this.navParams.get("startDate");
    this.endDate = this.navParams.get("endDate");

    this.getInitEventsIn();
    this.getInitEventsOut();
    
  }

  getInitEventsIn() {
    this.service.getEventService().getAllByDateRange(this.startDate-86400000, this.endDate, this.indexIn, this.count).subscribe(
      data => {
        this.indexIn = this.indexIn + this.count;
        let datajson = data.json();
        for (let d in datajson) {
          this.eventsIn.push(datajson[d]);
        }
      },
      err => {
        console.log(err)
      }
    );
  }

  getInitEventsOut() {
    this.service.getEventService().getAllByDateOver(this.endDate, this.indexOut, this.count).subscribe(
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


  doInfiniteIn(): Promise<any> {
    return new Promise((resolve) => {
      this.service.getEventService().getAllByDateRange(this.startDate-86400000, this.endDate, this.indexIn, this.count).subscribe(
        data => {
          let datajson = data.json();
          if (datajson.length != 0) {
            this.indexIn = this.indexIn + this.count;
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

  doInfinitOut(): Promise<any> {
    return new Promise((resolve) => {
      this.service.getEventService().getAllByDateOver(this.endDate, this.indexOut, this.count).subscribe(
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
}
