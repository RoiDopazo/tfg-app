import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from '../config'

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RouteServiceProvider {

  constructor(private http: Http) {}
  access: any;
  
  url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/route';

  getAll(index: Number, count: Number) {
    let url = this.url +  '?index=' + index + '&count=' + count;
    return (this.http.get(url));
  }

  create(route) {
    return (this.http.post(this.url, route));
  }

  update(route) {
    let url = this.url +  "/" + route.id;
    delete route["id"];
    delete route["days"];
    delete route["user"];
    return (this.http.put(url, route));
  }


  day_create(route){
    let url = this.url + "/" + route.id + "/day";
    return (this.http.post(url, {}));
  }

  setNumDays(route, numDays) {
    let url = this.url + "/" + route.id + "/day/setNumDays";
    let headers = new Headers({'Content-Type': 'application/json'});
    
    let options = new RequestOptions({ headers: headers });
    return (this.http.post(url, numDays, options));
  }
}
