import { Injectable } from '@angular/core';
import { Http } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { global, SERVER_PORT, HTTP_PROTOCOL, GM_API } from '../config'

@Injectable()
export class GoogleServiceProvider {

  constructor(private http: Http) {}
  access: any;
  
  url = "https://maps.googleapis.com/maps/api";
  
  
  getUrlServer() {
    return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/google'
  }

  getPlacePhoto(reference: String) {
    let url = this.url + "/place/photo?maxwidth=400&" + reference + "&key=" + GM_API;
    console.log(url);
    return (this.http.get(url));
  }

  getTravelInfoBatch(listDiasLugar) {
    let url = this.getUrlServer() + "/distanceMatrix/batch";
    return this.http.post(url, listDiasLugar);
  }

  getTravelInfo(oriLat, oriLng, destLat, destLng, travelMode) {
    let url = this.getUrlServer() + "/distanceMatrix?oriLat=" + oriLat + "&oriLng=" + oriLng + "&dstLat=" + destLat + "&dstLng=" + destLng + "&mode=" + travelMode;
    return this.http.get(url);
  }
}
