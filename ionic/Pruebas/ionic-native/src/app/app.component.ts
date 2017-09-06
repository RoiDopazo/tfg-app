import { Component, ViewChild } from '@angular/core';
import { Platform, Nav} from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { Events } from 'ionic-angular';

import { AuthService } from '../providers/auth-service';

import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  rootPage:any = LoginPage;
  public menu_pages: Array<{ title:string, component:any, icon:string }>;
  public user;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, private auth: AuthService, private events:Events) {

    this.menu_pages = [
      { title: "Inicio",             component: HomePage,               icon: "home" },
      { title: "xxxx",               component: "",                     icon: "home" },
      { title: "yyyy",               component: "",                     icon: "home" },
      { title: "zzzz",               component: "",                     icon: "home" },
    ];
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();

      this.events.subscribe("loggin", 
      (user, pass) => {
        console.log(user);
        this.user = user;
        },
      )
    });
  }
  
  public logout() {
    this.auth.logout();
    this.nav.setRoot(LoginPage);
  }
}

