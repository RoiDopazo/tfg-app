import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from './config';
import { RouteServiceProvider } from './route-service/route-service';
import { UserServiceProvider } from './user-service/user-service';

/*
  Generated class for the UserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ServiceManagerProvider {

  constructor(private routeServiceProvider: RouteServiceProvider, private userServiceProvider:UserServiceProvider) {}
  access: any;
  
  url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/route';


  getRouteService() {
      return this.routeServiceProvider;
  }

  getUserService() {
      return this.userServiceProvider;
  }
}
