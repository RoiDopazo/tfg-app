import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'; 
import { BackgroundGeolocation } from '@ionic-native/background-geolocation';
import { Geolocation, Geoposition } from '@ionic-native/geolocation';
import 'rxjs/add/operator/map';
import { ServiceManagerProvider } from '../services/service-manager'
import { ToastController } from 'ionic-angular';

/*
  Generated class for the AuthServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/


@Injectable()
export class LocationTrackerProvider {

  status: boolean = false;

    constructor(private serviceManagerProvider: ServiceManagerProvider, private backgroundGeolocation: BackgroundGeolocation, private geolocation: Geolocation) {
    }

    startTracking(routeId, day) {
      this.status = true;
        let config = {
          desiredAccuracy: 10,
          stationaryRadius: 20,
          distanceFilter: 10,
          debug: false,
          interval: 2000,
          stopOnTerminate: false,
        };
        
        this.backgroundGeolocation.configure(config).subscribe(
          
          (location) => {
            this.serviceManagerProvider.getRouteService().postData(routeId, day, location).subscribe(
            );  
          }, 
          (err) => {
            this.serviceManagerProvider.presentNativeToast("Error al obtener datos de geolocalización");
          });
        this.backgroundGeolocation.start();
      }
    
      stopTracking() {
        this.status = false;
        this.backgroundGeolocation.stop();
        //this.backgroundGeolocation.finish();
      }


      getStatus() {
        return this.status;
      }

}
