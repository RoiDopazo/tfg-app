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


  presentToast(url) {
    let toast = this.toastCtrl.create({
      message: url,
      duration: 5000,
      position: 'bottom'
    });
  
    toast.onDidDismiss(() => {
      console.log('Dismissed toast');
    });
  
    toast.present();
  }
  

  getUrl() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/route';
  }

  // Ruta endpoints

  getAll(index: Number, count: Number) {
    let url = this.getUrl() +  '?index=' + index + '&count=' + count;
    this.presentToast(url);
    return (this.http.get(url));
  }

  getById(id: Number) {
    let url = this.getUrl() + "/" + id;
    return (this.http.get(url));
  }

  create(route) {
    return (this.http.post(this.getUrl(), route));
  }

  update(route) {
    let url = this.getUrl() +  "/" + route.id;
    delete route["id"];
    delete route["days"];
    delete route["user"];
    return (this.http.put(url, route));
  }


  // Dia endpoitns

  day_create(route){
    let url = this.getUrl() + "/" + route.id + "/day";
    return (this.http.post(url, {}));
  }

  day_update(idRoute, day) {
    let url = this.getUrl() + "/" + idRoute + "/day";
    return (this.http.put(url, day));
  }

  day_calculateHours(idRoute, day) {
    let url = this.getUrl() + "/" + idRoute + "/day/calculateHours";
    return this.http.post(url, day);
  }

  setNumDays(route, numDays) {
    let url = this.getUrl() + "/" + route.id + "/day/setNumDays";
    let headers = new Headers({'Content-Type': 'application/json'});
    
    let options = new RequestOptions({ headers: headers });
    return (this.http.post(url, numDays, options));
  }

  batchCreateDelete(idRoute, daysBefore, daysAfter, place) {

    let url = this.getUrl() +  "/" +idRoute + "/day/alldays";

    let diaLugar = {
      "order": 0,
      "place": place
    };

    let body = {
      "daysBefore": daysBefore,
      "daysAfter": daysAfter,
      "diaLugar": diaLugar
    };

    console.log(body);
    return this.http.post(url, body);

  }



  // DiaLugar endpoints

  day_place_update_b(idRoute, idDay, dayPlaceList) {
    let url = this.getUrl() + "/" + idRoute + "/day/" + idDay + "/place";
    return (this.http.put(url, dayPlaceList));
  }
}
