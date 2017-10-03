import { Component, ViewChild, ElementRef } from '@angular/core';
import { Platform, IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  LatLng,
  CameraPosition,
  MarkerOptions,
  Marker
} from '@ionic-native/google-maps';

/**
 * Generated class for the Tab_Search_1Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

declare var google; 

@IonicPage()
@Component({
  selector: 'page-search',
  templateUrl: 'search.html',
})
export class SearchPage {

  // Segment selected
  private segment = 'map';
  // Google Map
  private map: GoogleMap;
  // Barra de tabs
  private tabbar;
  // Map Html element
  @ViewChild('googlemap') theMap: ElementRef;



  constructor(public navCtrl: NavController, public platform: Platform,  public navParams: NavParams, public googleMaps: GoogleMaps) {
  
    platform.ready().then(() => {
       //this.initMap();
      });
    this.hideTabbar();
  }


  autocom() {
    let input = document.getElementById('input');
    let options = {
      types: ['(cities)']
     };
    let autocomplete = new google.maps.places.Autocomplete(input, options);
    google.maps.event.addListener(autocomplete, "place_changed", () => {
      console.log(autocomplete);
      let place = autocomplete.getPlace();
      let latitude = place.geometry.location.lat();
      let longitude = place.geometry.location.lng();
      alert(latitude + ", " + longitude);
      console.log(place);
     },
     err => console.log(err)
  );
  
  }


  ngOnDestroy() {
    if (this.tabbar != null) {
        Object.keys(this.tabbar).map((key) => {
            this.tabbar[key].style.display = '';
        });
    }
}

showMap() {
    this.theMap.nativeElement.hidden = false;
}

hideMap() {
  
    this.theMap.nativeElement.hidden = true;
}

initMap() {
  // create a new map by passing HTMLElement
  let element: HTMLElement = document.getElementById('googlemap');
  console.log(this.googleMaps);

  this.map = this.googleMaps.create(element);
  console.log(this.map);
  // listen to MAP_READY event
  // You must wait for this event to fire before adding something to the map or modifying it in anyway
  this.map.one(GoogleMapsEvent.MAP_READY).then(
      () => {
          console.log('Map is ready!');
          // Now you can add elements to the map like the marker
      }
  );

  console.log("HOLA");
}

  hideTabbar() {
    this.tabbar = document.querySelectorAll(".tabbar");
    if (this.tabbar != null) {
        Object.keys(this.tabbar).map((key) => {
            this.tabbar[key].style.display = 'none';
        });
    }
  }


}
