import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HttpUtil {

    private headers: HttpHeaders;
    private options = {};

    constructor(
        private http: HttpClient
    ) {
        this.headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=UTF-8', 'X-Requested-With': 'XMLHttpRequest' });
        this.options = {headers: this.headers};
    }

    public get(url: string): Promise<any> {
        return this.http.get(url).toPromise();
    }

    public post(url: string, data: any): Promise<any> {
        return this.http.post(url, data, this.options)
            .toPromise();
    }

    public put(url: string, data: any): Promise<any> {
        return this.http.put(url, data, this.options)
            .toPromise();
    }

    public patch(url: string, data: any): Promise<any> {
        return this.http.patch(url, data, this.options)
            .toPromise();
    }

    public remove(url: string): Promise<any> {
        return this.http.delete(url, this.options)
            .toPromise();
    }

    public upload(url: string, data: any): Promise<any> {
        return this.http.post(url, data, {})
            .toPromise();
    }
}
