import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { UserService } from '../services/userservice';
import { Events } from 'ionic-angular';

export class User {
  name: string;
  email: string;

  constructor(name: string, email: string) {
    this.name = name;
    this.email = email;
  }
}

@Injectable()
export class AuthService {
  currentUser: User;
  access: any;

  constructor(private userService: UserService, private events:Events) { }

  public login(credentials) {
    if (credentials.username === null || credentials.password === null) {
      return new Promise(resolve => {
        setTimeout(() => {
          resolve(false);
        }, 2000);
      })
    } else {
      return new Promise(resolve => {
        if (credentials.username === "1234" && credentials.password === "1234") {
          this.currentUser = new User("1234", "1234");
          resolve(true);
        }
        setTimeout(() => {
          this.userService.checkCredential(credentials.username, credentials.password).subscribe(
            data => {
              console.log("data -----------");
              this.access = data.json();
              console.log("va hacer resolve");
              console.log(this.access);
              this.currentUser = new User(credentials.username, credentials.username);
              this.events.publish("loggin", credentials.username, credentials.password);
              resolve(this.access);
            },
            err => console.error(err),
            () => console.log('check credentials complete')
          );
        }, 2000);
      });
    }
  }

  public register(credentials) {
    if (credentials.username === null || credentials.password === null) {
      return new Promise(resolve => {
        setTimeout(() => {
          resolve(false);
        }, 2000);
      })
    } else {      
      return new Promise(resolve => {
        setTimeout(() => {
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
        }, 2000);
      });
    }
  }

  public getUserInfo(): User {
    return this.currentUser;
  }

  public logout() {
    this.currentUser = null;
  }
}



/*

 public login(credentials) {
    
    if (credentials.email === null || credentials.password === null) {
      return Observable.throw("Please insert credentials");
    } else {
      
      return Observable.create(observer => {
        // At this point make a request to your backend to make a real check!
        let access;
        this.userService.checkCredential(credentials.email, credentials.password).subscribe(
          data => access = data
        );
        //let access = (credentials.password === "pass" && credentials.email === "email");
        console.log(access);
        this.currentUser = new User(credentials.email, credentials.email);
        if (access) {
          observer.next(true);
          observer.complete();
        } else {
          observer.next(false);
          observer.complete();
        }
        
      });
    }
  }



let url = this.url + '/authenticate';

          let body = {
            'nombre': credentials.username,
            'password': credentials.password
          };

          this.http.post(url, body).subscribe(
            data => {
              console.log("data -----------");
              this.access = data.json();
              console.log("va hacer resolve");
              console.log(this.access);
              resolve(this.access);
            },
            err => console.error(err),
            () => console.log('check credentials complete')
          );
  */