import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { NavController} from 'ionic-angular';
import { SERVER_PORT, HTTP_PROTOCOL } from './config';
import { RouteServiceProvider } from './route-service/route-service';
import { PlaceServiceProvider } from './place-service/place-service';
import { UserServiceProvider } from './user-service/user-service';
import { EventServiceProvider } from './event-service/event-service';
import { CategoryServiceProvider } from './category-service/category-service';
import { GoogleServiceProvider } from './google-service/google-service';
import { FoursquareServiceProvider } from './foursquare-service/foursquare-service';
import { AuthServiceProvider } from '../auth-service/auth-service';
import { NativeStorage } from '@ionic-native/native-storage';



/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ServiceManagerProvider {

  constructor(private routeServiceProvider: RouteServiceProvider, private userServiceProvider:UserServiceProvider, 
    private googleServiceProvider: GoogleServiceProvider, private foursquareServiceProvider: FoursquareServiceProvider,
    private placeServiceProvider: PlaceServiceProvider, private categoryService: CategoryServiceProvider, private authServiceProvider: AuthServiceProvider, private eventService: EventServiceProvider, private nativeStorage: NativeStorage) {}


  getAuthService() {
    return this.authServiceProvider;
  }

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

  getCategoryService() {
    return this.categoryService;
  }

  getEventService() {
    return this.eventService;
  }

  handleError(err) {
    return new Promise(resolve => {
      if (err.json().type == "ExpiredJwtToken") {
        let refreshToken;
        this.nativeStorage.getItem('refreshToken')
        .then(
          data => {
            refreshToken = data.token;
            this.userServiceProvider.refreshToken(refreshToken).subscribe(
              data => {
                this.authServiceProvider.updateUserToken(data.json().token);
                this.nativeStorage.setItem('refreshToken', {token: data.json().refreshToken});
              },
              err => {
                resolve(false);
              }
            );
          },
          error => resolve(false)
        );
        
      }
    });
  }
}
