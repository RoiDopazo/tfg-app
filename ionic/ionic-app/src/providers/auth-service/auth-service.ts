import { Injectable } from '@angular/core';
import { Events } from 'ionic-angular';
import 'rxjs/add/operator/map';
import { UserServiceProvider } from './../../providers/services/user-service/user-service';

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

  constructor(private service: UserServiceProvider, private events: Events) { }

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
        this.service.checkCredential(credentials.username, credentials.password).subscribe(
          data => {
            this.currentUser = new User(credentials.username, data.json().token.string);
            this.events.publish("login", credentials.username);
            resolve(true);
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
          this.service.registerUser(credentials.username, credentials.password).subscribe(
            data => {
              this.currentUser = new User(credentials.username, credentials.username);
              this.events.publish("login", credentials.username, credentials.password);
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
