import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response  } from '@angular/http';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'


@Injectable()
export class EventServiceProvider {
    constructor(private http: Http) {}


    getUrl() {
        return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/';
      }

    getAll() {
        let url = this.getUrl() + 'event';
        return this.http.get(url);
    }
}