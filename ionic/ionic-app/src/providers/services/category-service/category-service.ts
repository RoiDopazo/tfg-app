import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response  } from '@angular/http';
import { global, SERVER_PORT, HTTP_PROTOCOL, SERVER_CONTEXT } from '../config';
import { AuthServiceProvider } from './../../auth-service/auth-service';

@Injectable()
export class CategoryServiceProvider {
    constructor(private http: Http, private authService: AuthServiceProvider) {}
    access: any;
    
    cat_url = HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + SERVER_CONTEXT + '/rest/category';
    subcat_url = HTTP_PROTOCOL + global.SERVER_IP + ':' + SERVER_PORT + SERVER_CONTEXT + '/rest/subcategory';

    getHeaders() {
        let headers = new Headers();
        headers.append('Authorization', 'Bearer ' + this.authService.getUserInfo().token);
        let options = new RequestOptions({ headers: headers });
        return options;
      }

    getAllCategories() {
        return (this.http.get(this.cat_url, this.getHeaders()));
    }

    getAllSubCategories() {
        return (this.http.get(this.subcat_url, this.getHeaders()));
    }
}