import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from '../config'

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class UserServiceProvider {

  constructor(private http: Http) {}
  access: any;
  
  url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/user';

  getExample() {
      let url = 'http://' + SERVER_IP + ':' + SERVER_PORT + '/rest/place';
      console.log(url);
      return (this.http.get(url));
  }


  checkCredential(username, password) {
      let url = this.url + '/authenticate';

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

      

      return this.http.post(this.url, body);
  }
}
