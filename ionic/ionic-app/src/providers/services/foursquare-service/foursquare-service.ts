import { Injectable } from '@angular/core';
import { Http } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from '../config'

@Injectable()
export class FoursquareServiceProvider {

  constructor(private http: Http) {}
  access: any;
  
  url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/foursquare';


  getPlacesByCity(idRoute: String, city: String, limit: Number, photosBool: String) {

    let url = this.url + '/findPlaces?route=' + idRoute + '&name=' + city +'&limit=' + limit + '&photo=' + photosBool;
    return this.http.get(url); 
  }

}
