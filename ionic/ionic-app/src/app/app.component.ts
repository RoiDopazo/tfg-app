import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { NativeStorage } from '@ionic-native/native-storage';
import { AuthServiceProvider } from './../providers/auth-service/auth-service';
import { Events } from 'ionic-angular';

import { HomePage } from '../pages/home/home';
@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage:any;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, private nativeStorage: NativeStorage, private authServiceProvider: AuthServiceProvider, public events: Events) {
    
    this.nativeStorage.getItem('user').then(
      data => {
        this.events.publish('user:get:stored', data);
        this.rootPage = "MaintabPage";
      },
      err => this.rootPage = "LoginPage"
      );
    

    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
    });
  }
}

