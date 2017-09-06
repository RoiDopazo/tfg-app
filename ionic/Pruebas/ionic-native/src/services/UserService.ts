import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response  } from '@angular/http';
import { SERVER_IP, SERVER_PORT, HTTP_PROTOCOL } from '../providers/config'

@Injectable()
export class UserService {
    constructor(private http: Http) {}
    access: any;
    
    url = HTTP_PROTOCOL + SERVER_IP + ':' + SERVER_PORT + '/rest/user';

    getExample() {
        let url = 'http://' + SERVER_IP + ':' + SERVER_PORT + '/rest/place';
        console.log(url);
        return (this.http.get(url));
    }


    checkCredential(username, password) {
        let url = this.url + '/authenticate';

        let body = {
            'nombre': username,
            'password': password
        };

        return this.http.post(url, body);
    }

    registerUser(username, password) {
        let body = {
            'nombre': username,
            'password': password,
            'email': "asdas@afdssf.com"
        };

        return this.http.post(this.url, body);
    }
}

/*

checkCredential(username, password) {
        let url = this.url + '/authenticate';

        let body = {
            'nombre': username,
            'password': password
        };

        return (this.http.post(url, body));



checkCredential(username, password) {
        let url = this.url + '/authenticate';

        let body = {
            'nombre': username,
            'password': password
        };

        this.http.post(url, body).subscribe(
            data => {
                console.log("data -----------");
                this.access = data.json();
            },
            err => console.error(err),
            () => console.log('checkCredential complete')
        );
        return this.access;
    }
    */
    