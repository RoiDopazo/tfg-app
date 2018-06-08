import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL, SERVER_CONTEXT } from '../config'

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class UserServiceProvider {

  constructor(private http: Http) {}
  access: any;


  getUrl() {
      return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + SERVER_CONTEXT + '/rest/user';
  }


  checkCredential(username, password) {
      let url = this.getUrl() + '/authenticate';

      let body = {
          'username': username,
          'password': password
      };

      return this.http.post(url, body);
  }

  registerUser(username, password, email) {
      let body = {
          'username': username,
          'password': password,
          'email': email
      };

      return this.http.post(this.getUrl(), body);
  }

  refreshToken(refreshToken) {
      let url = this.getUrl() + '/refreshtoken';
      let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let options = new RequestOptions({ headers: headers });
      return this.http.post(url, refreshToken, options);
  }

  updateUser(id, token, data) {
    let url = this.getUrl() + '/' + id;
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + token);
    let options = new RequestOptions({ headers: headers });
    return this.http.put(url, data, options);
  }

  getUserInfo(token) {
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + token);
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.getUrl(), options);
}


}
