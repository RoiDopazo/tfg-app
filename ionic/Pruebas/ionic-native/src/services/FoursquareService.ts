import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response  } from '@angular/http';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from '../providers/config'
import { AuthService } from '../providers/auth-service';

@Injectable()
export class FoursquareService {
    constructor(private http: Http, private auth: AuthService) {}
    access: any;
    
    url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/foursquare';

    getPlaces(city: String) {
        let url = this.url +  '/findPlaces?nombre=' + city;
        console.log(url);
        return (this.http.get(url));
    }
}