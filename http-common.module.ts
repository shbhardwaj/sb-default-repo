import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Http, HttpModule } from '@angular/http';
import { HttpService } from './http.service';
import { IHttpInterceptor } from './http-interceptor.interface';
import { AuthHttpInterceptor } from './auth-http-interceptor';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    { provide: HttpService, useFactory: httpServiceFactory, deps: [Http, <IHttpInterceptor[]>[AuthHttpInterceptor]] },
    AuthHttpInterceptor
  ]
})
export class HttpCommonModule { }

export function httpServiceFactory(http: Http, ...requestInterceptors: IHttpInterceptor[]) {
  return new HttpService(http, requestInterceptors);
}
