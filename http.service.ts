import { Injectable } from '@angular/core';
import { Http, RequestOptionsArgs, Response, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Subscriber } from 'rxjs/Subscriber';
import { IHttpService } from './http-service.interface';
import { IHttpInterceptor } from './http-interceptor.interface';

@Injectable()
export class HttpService implements IHttpService {

    constructor(private http: Http | any, private requestInterceptors: IHttpInterceptor[]) {

    }

    request(url: string | Request, options: RequestOptionsArgs = {}): Observable<Response> {
        this.requestConfig(options);
        return this.http.request(url, options).do((response: Response) => {
            return this.responseResolve(response);
        }).catch((error: Response) => {
            return this.errorResolve(error);
        });
    }

    delete(url: string, options: RequestOptionsArgs = {}): Observable<Response> {
        this.requestConfig(options);
        return this.http.delete(url, options).do((response: Response) => {
            return this.responseResolve(response);
        }).catch((error: Response) => {
            return this.errorResolve(error);
        });
    }

    head(url: string, options: RequestOptionsArgs = {}): Observable<Response> {
        this.requestConfig(options);
        return this.http.head(url, options).do((response: Response) => {
            return this.responseResolve(response);
        }).catch((error: Response) => {
            return this.errorResolve(error);
        });
    }

    patch(url: string, data: any, options: RequestOptionsArgs = {}): Observable<Response> {
        this.requestConfig(options);
        return this.http.patch(url, data, options).do((response: Response) => {
            return this.responseResolve(response);
        }).catch((error: Response) => {
            return this.errorResolve(error);
        });
    }

    post(url: string, data: any, options: RequestOptionsArgs = {}): Observable<Response> {
        this.requestConfig(options);
        return this.http.post(url, data, options).do((response: Response) => {
            return this.responseResolve(response);
        }).catch((error: Response) => {
            return this.errorResolve(error);
        });
    }

    put(url: string, data: any, options: RequestOptionsArgs = {}): Observable<Response> {
        this.requestConfig(options);
        return this.http.put(url, data, options).do((response: Response) => {
            return this.responseResolve(response);
        }).catch((error: Response) => {
            return this.errorResolve(error);
        });
    }

    private requestConfig(requestOptions: RequestOptionsArgs): void {
        this.requestInterceptors.forEach((interceptor: IHttpInterceptor) => {
            if (interceptor.onRequest) {
                requestOptions = interceptor.onRequest(requestOptions);
            }
        });
    }

    private responseResolve(response: Response): Observable<Response> {
        this.requestInterceptors.forEach((interceptor: IHttpInterceptor) => {
            if (interceptor.onResponse) {
                response = interceptor.onResponse(response);
            }
        });
        return new Observable<any>((subscriber: Subscriber<any>) => {
            subscriber.next(response);
        });
    }

    private errorResolve(error: Response): Observable<Response> {
        this.requestInterceptors.forEach((interceptor: IHttpInterceptor) => {
            if (interceptor.onResponseError) {
                error = interceptor.onResponseError(error);
            }
        });
        return new Observable<any>((subscriber: Subscriber<any>) => {
            subscriber.next(error);
        });
    }
}
