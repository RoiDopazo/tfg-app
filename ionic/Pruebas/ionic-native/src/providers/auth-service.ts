import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { UserService } from '../services/userservice';
import { Events } from 'ionic-angular';
import { NativeStorage } from '@ionic-native/native-storage';

export class User {
  name: String;
  token: String;


  constructor(name: string, token: string) {
    this.name = name;
    this.token = token;
  }
}



@Injectable()
export class AuthService {
  currentUser: User;
  access: any;

  constructor(private userService: UserService, private events: Events, private nativeStorage: NativeStorage) { }

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
        this.userService.checkCredential(credentials.username, credentials.password).subscribe(
          data => {
            this.access = data.json();
            this.nativeStorage.setItem('myitem', { property: 'value', anotherProperty: 'anotherValue' })
              .then(
              () => console.log('Stored item!'),
              error => console.error('Error storing item', error)
              );
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
          this.userService.registerUser(credentials.username, credentials.password).subscribe(
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
    this.currentUser.name = name;
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