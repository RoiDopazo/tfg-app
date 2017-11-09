import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response  } from '@angular/http';
import { global, SERVER_PORT, HTTP_PROTOCOL } from '../config'


@Injectable()
export class CategoryServiceProvider {
    constructor(private http: Http) {}
    access: any;
    
    cat_url = HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/category';
    subcat_url = HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + '/rest/subcategory';

    getAllCategories() {
        return (this.http.get(this.cat_url));
    }

    getAllSubCategories() {
        return (this.http.get(this.subcat_url));
    }
}