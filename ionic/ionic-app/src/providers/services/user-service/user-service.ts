import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'

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
      return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/user';
  }

  getExample() {
      let url = 'http://' + global.SERVER_IP + ':' + SERVER_PORT + '/rest/place';
      console.log(url);
      return (this.http.get(url));
  }


  checkCredential(username, password) {
      let url = this.getUrl() + '/authenticate';

      let body = {
          'nombre': username,
          'password': password
      };
      let headers = new Headers({'Content-Type': 'application/json'});
      headers.append('Authorization', 'X '+username);
      
      let options = new RequestOptions({ headers: headers });

      return this.http.post(url, body, options);
  }

  registerUser(username, password) {
      let body = {
          'nombre': username,
          'password': password,
          'email': "asdas@afdssf.com"
      };

      

      return this.http.post(this.getUrl(), body);
  }
}
