import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'
import { ToastController } from 'ionic-angular';
import { AuthServiceProvider } from './../../auth-service/auth-service';


/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RouteServiceProvider {

  constructor(private toastCtrl: ToastController, private http: Http, private authService: AuthServiceProvider) {}
  access: any;
  

  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/';
  }

  getHeaders() {
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
    let options = new RequestOptions({ headers: headers });
    return options;
  }

  // Ruta endpoints

  getAll(index: Number, count: Number) {
    let url = this.getUrl() +  'route?index=' + index + '&count=' + count;
    return (this.http.get(url, this.getHeaders()));
  }

  getById(id: Number) {
    let url = this.getUrl() + "route/" + id;
    return (this.http.get(url, this.getHeaders()));
  }

  create(route) {
    return (this.http.post(this.getUrl() + "route", route, this.getHeaders()));
  }

  update(route) {
    let url = this.getUrl() +  "route/" + route.id;
    delete route["id"];
    delete route["days"];
    delete route["user"];
    return (this.http.put(url, route, this.getHeaders()));
  }


  // Dia endpoitns

  day_create(route){
    let url = this.getUrl() + "route/" + route.id + "/day";
    return (this.http.post(url, this.getHeaders()));
  }

  day_update(idRoute, day) {
    let url = this.getUrl() + "route/" + idRoute + "/day";
    return (this.http.put(url, day, this.getHeaders()));
  }

  day_calculateHours(idRoute, day) {
    let url = this.getUrl() + "route/" + idRoute + "/day/calculateHours";
    return this.http.post(url, day, this.getHeaders());
  }

  setNumDays(route, numDays) {
    let url = this.getUrl() + "route/" + route.id + "/day/setNumDays";

    return (this.http.post(url, numDays, this.getHeaders()));
  }



  // Stay endpoints

  stay_create_delete_batch(idRoute, daysBefore, daysAfter, place) {

    let url = this.getUrl() +  "stay/place/batch?idRoute=" + idRoute;

    let stay = {
      "order": 0,
      "place": place
    };

    let body = {
      "daysBefore": daysBefore,
      "daysAfter": daysAfter,
      "stay": stay
    };

    console.log(body);
    return this.http.post(url, body, this.getHeaders());

  }

  stay_createByEvent(idRoute, idDay, eventPlace) {

    let url = this.getUrl() +  "stay/event?idRoute=" + idRoute + "&idDay=" + idDay;

    console.log(eventPlace);
    let body = {
      "eventPlace": eventPlace
    }
    return this.http.post(url, body, this.getHeaders());

  }

  stay_update_batch(stayList) {
    let url = this.getUrl() + "stay/batch";
    return (this.http.put(url, stayList, this.getHeaders()));
  }

  stay_delete(idStay) {
    let url = this.getUrl() + "stay/" + idStay;
    return this.http.delete(url, this.getHeaders());
  }
}
