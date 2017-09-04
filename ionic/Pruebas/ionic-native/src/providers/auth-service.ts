import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { UserService } from '../services/userservice';

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

  constructor(private userService: UserService) { }

  public login(credentials) {

    if (credentials.username === null || credentials.password === null) {
      return new Promise(resolve => {
        setTimeout(() => {
          resolve(false);
        }, 2000);
      })
    } else {
      return new Promise(resolve => {
        setTimeout(() => {
          this.userService.checkCredential(credentials.username, credentials.password).subscribe(
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
        }, 2000);
      })
    }
  }

  public register(credentials) {
    if (credentials.username === null || credentials.password === null) {
      return Observable.throw("Please insert credentials");
    } else {
      // At this point store the credentials to your backend!
      return Observable.create(observer => {
        observer.next(true);
        observer.complete();
      });
    }
  }

  public getUserInfo(): User {
    return this.currentUser;
  }

  public logout() {
    return Observable.create(observer => {
      this.currentUser = null;
      observer.next(true);
      observer.complete();
    });
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