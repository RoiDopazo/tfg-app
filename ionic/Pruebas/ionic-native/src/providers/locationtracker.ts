import { Injectable, NgZone } from '@angular/core';
import { BackgroundGeolocation } from '@ionic-native/background-geolocation';
import { Geolocation, Geoposition } from '@ionic-native/geolocation';
import { FoursquareService } from '../services/FoursquareService';
import 'rxjs/add/operator/filter';

@Injectable()
export class LocationTracker {

    public watch: any;    
    public lat: number = 0;
    public lng: number = 0;
    public time: number = 0;

    

    constructor(private zone: NgZone, private backgroundGeolocation: BackgroundGeolocation, private geolocation: Geolocation, private foursquareService: FoursquareService) {
      
    }

    startTracking() {
         // Background Tracking
        
         let config = {
           desiredAccuracy: 10,
           stationaryRadius: 20,
           distanceFilter: 20, 
           debug: false,
           interval: 2000 
         };
        
         this.backgroundGeolocation.configure(config).subscribe((location) => {
        
           console.log('BackgroundGeolocation:  ' + location.latitude + ',' + location.longitude);
        
           // Run update inside of Angular's zone
           this.zone.run(() => {
             this.lat = location.latitude;
             this.lng = location.longitude;
             this.time = location.time;
             this.foursquareService.sendPos(this.lat, this.lng, this.time).subscribe();
           });
        
         }, (err) => {
          this.foursquareService.sendPos(1, 2, 1).subscribe(
          
          );
           console.log("errooooooor");
           console.log(err);
        
         },
        () => {
          this.foursquareService.sendPos(0, 0, 0).subscribe();
        });    

        this.backgroundGeolocation.start();
       }

       stopTracking() {
        
         console.log('stopTracking');
         let location = this.backgroundGeolocation.getLocations();
         console.log(location);
         this.foursquareService.sendPos(this.lat, this.lng, this.time).subscribe();
         this.backgroundGeolocation.finish();
         this.backgroundGeolocation.stop();
        
       }
}