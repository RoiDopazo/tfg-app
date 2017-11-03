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

  getPlacesByCity(idRoute: String, city: String, limit: Number, photosBool: String) {

    let url = this.getUrl() + '/findPlaces?route=' + idRoute + '&name=' + city +'&limit=' + limit + '&photo=' + photosBool;
    return this.http.get(url); 
  }
}
