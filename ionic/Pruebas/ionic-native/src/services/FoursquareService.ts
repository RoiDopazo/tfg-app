import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response  } from '@angular/http';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from '../providers/config'
import { AuthService } from '../providers/auth-service';

@Injectable()
export class FoursquareService {
    constructor(private http: Http, private auth: AuthService) {}
    access: any;
    
    url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/foursquare';

    getPlaces(city: String, limit: Number, categoria: String, photo: String) {
        let url = this.url +  '/findPlaces?name=' + city + '&limit=' + limit + '&category=' + categoria + '&photo=' + photo;
        console.log(url);
        return (this.http.get(url));
    }

    sendPos(lat: Number, lng: Number, timeNow: Number) {
        let url = this.url +  '/getcoord?lat=' + lat + '&lng=' + lng + '&time=' + timeNow;
        console.log(url);
        return (this.http.get(url));
    }
}