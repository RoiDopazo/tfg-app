import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL, GM_API } from '../config'
import { AuthServiceProvider } from './../../auth-service/auth-service';

declare var google;

@Injectable()
export class GoogleServiceProvider {

  constructor(private http: Http, private authService: AuthServiceProvider) { }
  access: any;

  url = "https://maps.googleapis.com/maps/api";


  getUrlServer() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/google'
  }

  getHeaders() {
    let headers = new Headers();
    headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
    let options = new RequestOptions({ headers: headers });
    return options;
  }
  

  getPlacePhoto(reference: String) {
    let url = this.url + "/place/photo?maxwidth=400&" + reference + "&key=" + GM_API;
    console.log(url);
    return (this.http.get(url, this.getHeaders()));
  }

  getTravelInfoBatch(listDiasLugar) {
    let url = this.getUrlServer() + "/distanceMatrix/batch";
    return this.http.post(url, listDiasLugar, this.getHeaders());
  }

  getTravelInfo(oriLat, oriLng, destLat, destLng, travelMode) {
    let url = this.getUrlServer() + "/distanceMatrix?oriLat=" + oriLat + "&oriLng=" + oriLng + "&dstLat=" + destLat + "&dstLng=" + destLng + "&mode=" + travelMode;
    return this.http.get(url, this.getHeaders());
  }


  // DirectionsRenderer con el api web
  getPointsRoute(oriLat, oriLng, destLat, destLng) {

    return new Promise(resolve => {
      let directionsService = new google.maps.DirectionsService;
      directionsService.route({
        origin: {
          lat: oriLat,
          lng: oriLng
        },
        destination: {
          lat: destLat,
          lng: destLng
        },
        travelMode: google.maps.TravelMode['WALKING']
      }, (res, status) => {

        if (status == google.maps.DirectionsStatus.OK) {
          let coords = [];
          coords.push({
            lat: oriLat,
            lng: oriLng
          });
          coords.push({
            lat: res.routes[0].legs[0].steps[0].start_location.lat(),
            lng: res.routes[0].legs[0].steps[0].start_location.lng()
          });
          for (let step of res.routes[0].legs[0].steps) {
            
            for (let latlng of step.lat_lngs) {
              coords.push({
                lat: latlng.lat(),
                lng: latlng.lng()
              });
            }      
          }
          coords.push({
            lat: destLat,
            lng: destLng
          });

          resolve(coords);
        } else {
          console.warn(status);
        }
      });
    })
  }
}
