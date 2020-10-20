import { Http, RequestOptionsArgs, Response, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Subscriber } from 'rxjs/Subscriber';

export interface IHttpService {
    request(url: string | Request, options: RequestOptionsArgs): Observable<Response>;
    delete(url: string, options: RequestOptionsArgs): Observable<Response>;
    head(url: string, options: RequestOptionsArgs): Observable<Response>;
    patch(url: string, data: any, options: RequestOptionsArgs): Observable<Response>;
    post(url: string, data: any, options: RequestOptionsArgs): Observable<Response>;
    put(url: string, data: any, options: RequestOptionsArgs): Observable<Response>;
}
