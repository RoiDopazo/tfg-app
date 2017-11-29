import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ServiceManagerProvider } from '../../providers/services/service-manager';

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
  private events;

  constructor(public navCtrl: NavController, public navParams: NavParams, private service: ServiceManagerProvider) {
    this.select = "onTrip";
    this.service.getEventService().getAll().subscribe( 
      data => {
        this.events = data.json();
        console.log(this.events);
      },
      err => {
        console.log(err)
      }
    );
    
  }

}
