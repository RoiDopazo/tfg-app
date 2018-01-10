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
        let url = this.getUrl() + 'eventday';
        return this.http.get(url);
    }

    getAllByDateRange(startDate, endDate, index, count) {
        let url = this.getUrl() + 'eventday/date_between?value1=' + startDate + '&value2=' + endDate + '&index=' + index + '&count=' + count;
        return this.http.get(url);
    }

    getAllByDateOver(date, index, count) {
        let url = this.getUrl() + 'eventday/date_over?value=' + date + '&index=' + index + '&count=' + count;
        return this.http.get(url);
    }

    getEventNameByEventPlace(idEventPlace) {
        let url = this.getUrl() + 'eventplace/getEventNameByEventPlace?id=' + idEventPlace;
        return this.http.get(url);
    }
}