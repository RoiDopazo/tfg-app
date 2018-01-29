import { Injectable } from '@angular/core';
import { Http } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'

@Injectable()
export class FoursquareServiceProvider {

  constructor(private http: Http) {}
  access: any;

  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/foursquare';
  }

  getPlacesByCity(idRoute: String, lat: String, lng: String, radius: Number, section: String, query: String, limit: Number, sortByDistance: Number, price: String, photo: Boolean) {

    let url = this.getUrl() + '/recommendedPlaces?route=' + idRoute + '&lat=' + lat + '&lng=' + lng + '&radius=' + radius + '&section=' + section + '&query=' + query + '&limit=' + limit + '&sortByDistance=' + sortByDistance + '&price=' + price + '&photo=' + photo;
    return this.http.get(url); 
  }
}
