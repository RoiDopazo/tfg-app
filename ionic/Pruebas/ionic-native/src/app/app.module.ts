import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { NavController, IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { GoogleMaps } from '@ionic-native/google-maps';
import { HttpModule } from '@angular/http';
import { NativeStorage } from '@ionic-native/native-storage';
import { BackgroundGeolocation } from '@ionic-native/background-geolocation';
import { Geolocation } from '@ionic-native/geolocation';

import { AuthService } from './../providers/auth-service';
import { LocationTracker } from './../providers/locationtracker';
import { UserService } from '../services/userservice';
import { FoursquareService } from '../services/FoursquareService';
import { CategoryService } from '../services/CategoryService';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { RegisterPage } from '../pages/register/register';
import { ListapiPage } from '../pages/listapi/listapi';
import { TabsPage } from '../pages/tabs/tabs'
import { SearchTabPage } from '../pages/tabs/searchTab/searchTab';
import { SearchView1Page } from '../pages/tabs/searchTab/pages/searchview1/searchview1';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    RegisterPage,
    ListapiPage,
    TabsPage,
    SearchTabPage,
    SearchView1Page
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    RegisterPage,
    ListapiPage,
    TabsPage,
    SearchTabPage,
    SearchView1Page
  ],
  providers: [
    Geolocation,
    BackgroundGeolocation,
    LocationTracker,
    NativeStorage,
    GoogleMaps,
    StatusBar,
    SplashScreen,
    AuthService,
    UserService,
    FoursquareService,
    CategoryService,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
