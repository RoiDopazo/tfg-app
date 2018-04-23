import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config';
import { AuthServiceProvider } from './../../auth-service/auth-service';

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class UserServiceAuthProvider {

  constructor(private http: Http, private authService: AuthServiceProvider) {}
  access: any;


  getUrl() {
      return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/user';
  }

  getHeaders() {
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
    let options = new RequestOptions({ headers: headers });
    return options;
  }

  updateUser(id, data) {
    let url = this.getUrl() + '/' + id;
    return this.http.put(url, data, this.getHeaders());
  }

  getUserInfo() {
    return this.http.get(this.getUrl(), this.getHeaders());
}


}
