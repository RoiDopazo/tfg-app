import { Injectable } from '@angular/core';
import { Http } from '@angular/http'; 
import 'rxjs/add/operator/map';
import { SERVER_PORT, HTTP_PROTOCOL, GM_API } from '../config'

@Injectable()
export class GoogleServiceProvider {

  constructor(private http: Http) {}
  access: any;
  
  url = "https://maps.googleapis.com/maps/api";

  getPlacePhoto(reference: String) {
    let url = this.url + "/place/photo?maxwidth=400&" + reference + "&key=" + GM_API;
    console.log(url);
    return (this.http.get(url));
  }
}
