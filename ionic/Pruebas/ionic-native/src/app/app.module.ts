import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { NavController, IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { GoogleMaps } from '@ionic-native/google-maps';
import { HttpModule } from '@angular/http';
import { NativeStorage } from '@ionic-native/native-storage';

import { AuthService } from './../providers/auth-service';
import { UserService } from '../services/userservice';
import { FoursquareService } from '../services/FoursquareService';

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
    NativeStorage,
    GoogleMaps,
    StatusBar,
    SplashScreen,
    AuthService,
    UserService,
    FoursquareService,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
