import { Component, ViewChild, ElementRef } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { CalendarModal, CalendarModalOptions, DayConfig } from "ion2-calendar";
import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  LatLng,
  CameraPosition,
  MarkerOptions,
  Marker
} from '@ionic-native/google-maps';
import { ServiceManagerProvider } from '../../../providers/services/service-manager';


/**
 * Generated class for the Tab_2Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */


declare var google;

@IonicPage()
@Component({
  selector: 'page-tab-2',
  templateUrl: 'tab-2.html',
})
export class Tab_2Page {

  private city_to_search;
  private autocomplete;

  private ruta = {
    "city" : ""
  };
  private photo;
  private start_date = "Fecha Inicio";
  private end_date = "Fecha Fin";

  private map: GoogleMap;
  @ViewChild('googlemap') theMap: ElementRef;

  constructor(public navCtrl: NavController, public navParams: NavParams, private googleMaps: GoogleMaps, private modalCtrl: ModalController, private serviceManagerProvider: ServiceManagerProvider) {

  }

  ionViewDidLoad() {
    this.autocom();
  }

  autocom() {
    let input = document.querySelector('.searchbar-input');
    console.log(input);
    let options = {
      types: ['(cities)']
    };
    this.autocomplete = new google.maps.places.Autocomplete(input, options);
    google.maps.event.addListener(this.autocomplete, "place_changed", () => {
      this.city_to_search = this.autocomplete.getPlace();
      console.log(this.city_to_search);
      this.photo = this.city_to_search.photos[0].getUrl({"maxWidth": 300, "maxHeight": 300});
      this.ruta.city = this.city_to_search.name;
      
      if (!this.map) {
        //this.initMap();
      } else {
        //this.showCity();
      }
    },
      err => console.log(err)
    );

  }

  initMap() {
    let element: HTMLElement = document.getElementById('googlemap');

    this.map = this.googleMaps.create(element);
    this.map.one(GoogleMapsEvent.MAP_READY).then(
      () => {
        this.showCity();
      }
    );
  }

  showCity() {
    this.map.clear();
    // listen to MAP_READY event
    // You must wait for this event to fire before adding something to the map or modifying it in anyway
    let target = { "lat": this.city_to_search.geometry.location.lat(), "lng": this.city_to_search.geometry.location.lng() };
    this.map.moveCamera({
      'target': target,
      'tilt': 80,
      'zoom': 10,
    });

    this.map.addMarker({
      title: 'Ionic',
      icon: 'red',
      animation: 'DROP',
      position: {
        lat: this.city_to_search.geometry.location.lat(),
        lng: this.city_to_search.geometry.location.lng()
      }
    });
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
        this.start_date = date.from.string;
        this.end_date = date.to.string;
      } else {
        this.start_date = "Obligatorio"
        this.end_date = "Obligatorio"
      }
    });
  }

  continue() {
    
    this.serviceManagerProvider.getRouteService().create(this.ruta).subscribe(
      data => {
        this.navCtrl.push("MainPanelPage", {
          param1: data.json(),
          param2: this.start_date,
          param3: this.end_date,
          param4: this.photo
        });
      },
      err => console.log(err)
    );
  }
}
