import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL, SERVER_CONTEXT } from '../config';
import { AuthServiceProvider } from './../../auth-service/auth-service';

@Injectable()
export class FoursquareServiceProvider {

  constructor(private http: Http, private authService: AuthServiceProvider) {}
  access: any;

  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + SERVER_CONTEXT +'/rest/foursquare';
  }

  getHeaders() {
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
    let options = new RequestOptions({ headers: headers });
    return options;
  }


  getRecommendedPlaces(idRoute: String, lat: String, lng: String, radius: Number, section: String, query: String, limit: Number, sortByDistance: Number, price: String, photo: Boolean) {
    let url = this.getUrl() + '/recommendedPlaces?route=' + idRoute + '&lat=' + lat + '&lng=' + lng + '&radius=' + radius + '&section=' + section + '&query=' + query + '&limit=' + limit + '&sortByDistance=' + sortByDistance + '&price=' + price + '&photo=' + photo;
    return this.http.get(url, this.getHeaders()); 
  }

  searchPlaces(idRoute: String, lat: String, lng: String, radius: Number, query: String, limit: Number, category: String, photo: Boolean) {

    let url = this.getUrl() + '/searchPlaces?route=' + idRoute + '&lat=' + lat + '&lng=' + lng + '&radius=' + radius + '&query=' + query + '&limit=' + limit + '&category=' + category + '&photo=' + photo;
    return this.http.get(url, this.getHeaders()); 
  }
}
