import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL, SERVER_CONTEXT } from '../config';
import { AuthServiceProvider } from './../../auth-service/auth-service';

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class PlaceServiceProvider {

  constructor(private http: Http, private authService: AuthServiceProvider) {}
  access: any;
  
  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + SERVER_CONTEXT +'/rest/place'
  }

  getHeaders() {
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
    let options = new RequestOptions({ headers: headers });
    return options;
  }

  getListDaysByRouteAndFoursqueare(idRoute, idFoursquare) {
      let url = this.getUrl() + "/getListDaysBy?route=" + idRoute + "&idFoursquare=" + idFoursquare;
      return this.http.get(url, this.getHeaders()); 
  }
}
