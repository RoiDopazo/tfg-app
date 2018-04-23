import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config';
import { AuthServiceProvider } from './../../auth-service/auth-service';

@Injectable()
export class EventServiceProvider {
    constructor(private http: Http, private authService: AuthServiceProvider) { }


    getUrl() {
        return HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/';
    }

    getHeaders() {
        let headers = new Headers();
        headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
        let options = new RequestOptions({ headers: headers });
        return options;
    }

    getAll() {
        let url = this.getUrl() + 'eventday';
        return this.http.get(url, this.getHeaders());
    }

    getAllByDateRange(city, startDate, endDate, index, count) {
        let url = this.getUrl() + 'eventday/date_between?city=' + city + '&value1=' + startDate + '&value2=' + endDate + '&index=' + index + '&count=' + count;
        return this.http.get(url, this.getHeaders());
    }

    getAllByDateOver(city, date, index, count) {
        let url = this.getUrl() + 'eventday/date_over?city=' + city + '&value=' + date + '&index=' + index + '&count=' + count;
        return this.http.get(url, this.getHeaders());
    }

    getEventNameByEventPlace(idEventPlace) {
        let url = this.getUrl() + 'eventplace/getEventNameByEventPlace?id=' + idEventPlace;
        return this.http.get(url, this.getHeaders());
    }
}