import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AuthActions } from '../../reducers/login/index';
import { IHttpInterceptor } from './http-interceptor.interface';
import { RequestOptionsArgs, Response } from '@angular/http';

@Injectable()
export class AuthHttpInterceptor implements IHttpInterceptor {
    constructor(private store: Store<any>) {

    }

    onRequest(requestOptions: RequestOptionsArgs) {
        console.log('Intercept request', JSON.stringify(requestOptions));
        return requestOptions;
    }

    onResponse(response: Response) {
        console.log('Intercept response', JSON.stringify(response));
        return response;
    }

    onResponseError(response: Response) {
        console.log('Intercept error', JSON.stringify(response));
        if (response.status === 401) { // User is Unauthorized
            this.store.dispatch(AuthActions.loginFailed(response.json().message));
        }
        return response;
    }
}
