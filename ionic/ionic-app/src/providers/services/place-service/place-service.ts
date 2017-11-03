import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class PlaceServiceProvider {

  constructor(private http: Http) {}
  access: any;
  
  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/place'
  }

  getListDaysByRouteAndFoursqueare(idRoute, idFoursquare) {
      let url = this.getUrl() + "/getListDaysBy?route=" + idRoute + "&idFoursquare=" + idFoursquare;
      console.log(url);
      return this.http.get(url); 
  }
}
