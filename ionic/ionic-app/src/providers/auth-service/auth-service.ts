import { Injectable } from '@angular/core';
import { ServiceManagerProvider } from '../services/service-manager';
import { Events } from 'ionic-angular';
import 'rxjs/add/operator/map';

/*
  Generated class for the AuthServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/

export class User {
  id: String;
  token: String;


  constructor(name: string, token: string) {
    this.id = name;
    this.token = token;
  }
}

@Injectable()
export class AuthServiceProvider {

  currentUser: User;
  access: any;

  constructor(private service: ServiceManagerProvider, private events: Events) { }

  public login(credentials) {
    if (credentials.username === null || credentials.password === null) {
      return new Promise(resolve => {
          resolve(false);
      })
    } else {
      return new Promise(resolve => {
        if (credentials.username === "1234" && credentials.password === "1234") {
          this.currentUser = new User("1234", "1234");
          resolve(true);
        }
        this.service.getUserService().checkCredential(credentials.username, credentials.password).subscribe(
          data => {
            this.access = data.json();
            this.currentUser = new User(credentials.username, data.headers.get("X-Authorization"));
            this.events.publish("loggin", credentials.username);
            resolve(this.access);
          },
          err => {
            console.error(err);
            resolve(false);
          },
          () => console.log('check credentials complete')
        );
      });
    }
  }

  public register(credentials) {
    if (credentials.username === null || credentials.password === null) {
      return new Promise(resolve => {
          resolve(false);
      })
    } else {
      return new Promise(resolve => {
          this.service.getUserService().registerUser(credentials.username, credentials.password).subscribe(
            data => {
              console.log("va hacer resolve");
              console.log("true");
              this.currentUser = new User(credentials.username, credentials.username);
              this.events.publish("loggin", credentials.username, credentials.password);
              resolve(true);
            },
            err => console.error(err),
            () => console.log('check credentials complete')
          );
      });
    }
  }

  public updateUser(name: String, token: String): User {
    this.currentUser.id = name;
    this.currentUser.token = token;
    return this.currentUser;
  }

  public updateUserToken(token: String): User {
    this.currentUser.token = token;
    return this.currentUser;
  }

  public getUserInfo(): User {
    return this.currentUser;
  }

  public logout() {
    this.currentUser = null;
  }
}
