import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { HttpModule } from '@angular/http';
import { GoogleMaps } from '@ionic-native/google-maps';
import { CalendarModule } from "ion2-calendar";
import { Toast } from '@ionic-native/toast';
import { BackgroundGeolocation } from '@ionic-native/background-geolocation';
import { Geolocation } from '@ionic-native/geolocation';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { AuthServiceProvider } from '../providers/auth-service/auth-service';
import { UserServiceProvider } from '../providers/services/user-service/user-service';
import { CategoryServiceProvider } from '../providers/services/category-service/category-service';
import { EventServiceProvider } from '../providers/services/event-service/event-service';
import { RouteServiceProvider } from '../providers/services/route-service/route-service';
import { PlaceServiceProvider } from '../providers/services/place-service/place-service';
import { GoogleServiceProvider } from '../providers/services/google-service/google-service';
import { FoursquareServiceProvider } from '../providers/services/foursquare-service/foursquare-service';
import { ServiceManagerProvider } from '../providers/services/service-manager';
import { LocationTrackerProvider } from '../providers/location-tracker/location-tracker';
import { NativeStorage } from '@ionic-native/native-storage';


@NgModule({
  declarations: [
    MyApp,
    HomePage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule,
    CalendarModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage
  ],
  providers: [
    NativeStorage,
    LocationTrackerProvider,
    BackgroundGeolocation,
    Geolocation,
    GoogleMaps,
    StatusBar,
    SplashScreen,
    Toast,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthServiceProvider,
    UserServiceProvider,
    CategoryServiceProvider,
    EventServiceProvider,
    RouteServiceProvider,
    PlaceServiceProvider,
    GoogleServiceProvider,
    FoursquareServiceProvider,
    ServiceManagerProvider
  ]
})
export class AppModule {}
