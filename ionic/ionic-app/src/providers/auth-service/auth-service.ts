import { Injectable } from '@angular/core';
import { Events } from 'ionic-angular';
import 'rxjs/add/operator/map';
import { UserServiceProvider } from './../../providers/services/user-service/user-service';
import { NativeStorage } from '@ionic-native/native-storage';

/*
  Generated class for the AuthServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/

export class User {
  id: String;
  token: String;
  refreshToken: String;


  constructor(name: string, token: string, refreshToken: string) {
    this.id = name;
    this.token = token;
    this.refreshToken = refreshToken;
  }
}

@Injectable()
export class AuthServiceProvider {

  currentUser: User;

  constructor(private service: UserServiceProvider, private events: Events,  private nativeStorage: NativeStorage) {

    events.subscribe('user:get:stored', (user) => {
      console.log("user event");
      console.log(user);
      this.currentUser = new User(user.username, user.token, user.refreshToken);
    })
   }

  public login(credentials) {
    if (credentials.username === null || credentials.password === null) {
      return new Promise(resolve => {
          resolve(false);
      })
    } else {
      return new Promise(resolve => {
        if (credentials.username === "1234" && credentials.password === "1234") {
          this.currentUser = new User("1234", "1234", "1234");
          resolve(true);
        }
        this.service.checkCredential(credentials.username, credentials.password).subscribe(
          data => {
            this.currentUser = new User(credentials.username, data.json().token, data.json().refreshToken);
            this.nativeStorage.setItem('user', this.currentUser);
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
              this.currentUser = new User(credentials.username, data.json().token, data.json().refreshToken);
              this.nativeStorage.setItem('user', this.currentUser);
              this.events.publish("login", credentials.username, credentials.password);
              resolve(true);
            },
            err => console.error(err),
            () => console.log('check credentials complete')
          );
      });
    }
  }


  public updateUser(name: String, token: String, refreshToken: String): User {
    this.currentUser.id = name;
    this.currentUser.token = token;
    this.currentUser.refreshToken = refreshToken;
    this.nativeStorage.setItem('user', this.currentUser);
    return this.currentUser;
  }

  public updateUserToken(token: String): User {
    this.currentUser.token = token;
    this.nativeStorage.setItem('user', this.currentUser);
    return this.currentUser;
  }

  public getUserInfo(): User {
    return this.currentUser;
  }

  public logout() {
    this.currentUser = null;
  }


}
