import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'
import { ToastController } from 'ionic-angular';

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RouteServiceProvider {

  constructor(private toastCtrl: ToastController, private http: Http) {}
  access: any;

  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/';
  }

  // Ruta endpoints

  getAll(index: Number, count: Number) {
    let url = this.getUrl() +  'route?index=' + index + '&count=' + count;
    return (this.http.get(url));
  }

  getById(id: Number) {
    let url = this.getUrl() + "route/" + id;
    return (this.http.get(url));
  }

  create(route) {
    return (this.http.post(this.getUrl() + "route", route));
  }

  update(route) {
    let url = this.getUrl() +  "route/" + route.id;
    delete route["id"];
    delete route["days"];
    delete route["user"];
    return (this.http.put(url, route));
  }


  // Dia endpoitns

  day_create(route){
    let url = this.getUrl() + "route/" + route.id + "/day";
    return (this.http.post(url, {}));
  }

  day_update(idRoute, day) {
    let url = this.getUrl() + "route/" + idRoute + "/day";
    return (this.http.put(url, day));
  }

  day_calculateHours(idRoute, day) {
    let url = this.getUrl() + "route/" + idRoute + "/day/calculateHours";
    return this.http.post(url, day);
  }

  setNumDays(route, numDays) {
    let url = this.getUrl() + "route/" + route.id + "/day/setNumDays";
    let headers = new Headers({'Content-Type': 'application/json'});
    
    let options = new RequestOptions({ headers: headers });
    return (this.http.post(url, numDays, options));
  }



  // Stay endpoints

  stay_create_delete_batch(idRoute, daysBefore, daysAfter, place) {

    let url = this.getUrl() +  "stay/batch?idRoute=" + idRoute;

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
    return this.http.post(url, body);

  }

  stay_update_batch(idRoute, idDay, stayList) {
    let url = this.getUrl() + "stay/batch";
    return (this.http.put(url, stayList));
  }
}
