import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from './config';
import { RouteServiceProvider } from './route-service/route-service';
import { PlaceServiceProvider } from './place-service/place-service';
import { UserServiceProvider } from './user-service/user-service';
import { GoogleServiceProvider } from './google-service/google-service';
import { FoursquareServiceProvider } from './foursquare-service/foursquare-service';


/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ServiceManagerProvider {

  constructor(private routeServiceProvider: RouteServiceProvider, private userServiceProvider:UserServiceProvider, private googleServiceProvider: GoogleServiceProvider, private foursquareServiceProvider: FoursquareServiceProvider, private placeServiceProvider: PlaceServiceProvider) {}
  access: any;
  
  url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/route';


  getRouteService() {
      return this.routeServiceProvider;
  }

  getUserService() {
      return this.userServiceProvider;
  }

  getGoogleService() {
    return this.googleServiceProvider;
  }

  getFoursquareService() {
    return this.foursquareServiceProvider;
  }

  getPlaceService() {
    return this.placeServiceProvider;
  }
}
