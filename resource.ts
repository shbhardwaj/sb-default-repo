/*
https://github.com/Paldom/angular2-rest
*/
import { Inject } from '@angular/core';
import {
    Http, Headers as AngularHeaders,
    Request, RequestOptions, RequestMethod as RequestMethods,
    Response,
    URLSearchParams,
    ResponseContentType
} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './http.service';



export interface ResourceConfigParam {
    url: string;
}

/**
 * Set the base URL of REST resource
 * @param {String} url - base URL
 */
export function ResourceConfig(param: ResourceConfigParam) {
    return function (target: { new (http: HttpService): Resource<any> }) {
        target.prototype.getBaseUrl = function () {
            return param.url;
        };
    };
}

/**
 * GET method
 * @param {string} url - resource url of the method
 */
export const GET = methodBuilder(RequestMethods.Get);
/**
 * POST method
 * @param {string} url - resource url of the method
 */
export const POST = methodBuilder(RequestMethods.Post);
/**
 * PUT method
 * @param {string} url - resource url of the method
 */
export const PUT = methodBuilder(RequestMethods.Put);
/**
 * DELETE method
 * @param {string} url - resource url of the method
 */
export const DELETE = methodBuilder(RequestMethods.Delete);
/**
 * HEAD method
 * @param {string} url - resource url of the method
 */
export const HEAD = methodBuilder(RequestMethods.Head);


const urlBuilder = paramBuilder('Url');
export function Url(value: string) {
    return urlBuilder(value);
}

const pathBuilder = paramBuilder('Path');
export function Path(value: string) {
    return pathBuilder(value);
}

const queryParamBuilder = paramBuilder('Query')('query');
export function Query(target: Resource<any>, propertyKey: string | symbol, parameterIndex: number) {
    return queryParamBuilder(target, propertyKey, parameterIndex);
}

const bodyBuilder = paramBuilder('Body')('Body');
export function Body(target: Resource<any>, propertyKey: string | symbol, parameterIndex: number) {
    return bodyBuilder(target, propertyKey, parameterIndex);
}

const headerBuilder = paramBuilder('Header');
export function Header(value: string) {
    return headerBuilder(value);
}

export function Headers(headersObjKeyValPair: any) {
    return function (target: Resource<any>, propertyKey: string, descriptor: any) {
        descriptor.headers = headersObjKeyValPair;
        return descriptor;
    };
}

export function Produces(responseContentType: ResponseContentType) {
    return function (target: Resource<any>, propertyKey: string, descriptor: any) {
        descriptor.responseContentType = responseContentType;
        return descriptor;
    };
}

/**
 * Set default headers for every method of the RESTClient
 * @param {Object} headers - deafult headers in a key-value pair
 */
export function DefaultHeaders(headers: any) {
    return function <TFunction extends Function>(Target: TFunction): TFunction {
        Target.prototype.getDefaultHeaders = function () {
            return headers;
        };
        return Target;
    };
}


function paramBuilder(paramName: string) {
    return function (key: string) {
        return function (target: Resource<any>, propertyKey: string | symbol, parameterIndex: number) {
            const metadataKey = `${propertyKey}_${paramName}_parameters`;
            const paramObj: any = {
                key: key,
                parameterIndex: parameterIndex
            };
            if (Array.isArray(target[metadataKey])) {
                target[metadataKey].push(paramObj);
            } else {
                target[metadataKey] = [paramObj];
            }
        };
    };
}

function methodBuilder(method: RequestMethods) {
    return function (url?: string) {
        return function (target: Resource<any>, propertyKey: string, descriptor: any) {

            const pUrl = target[`${propertyKey}_Url_parameters`];
            const pPath = target[`${propertyKey}_Path_parameters`];
            const pQuery = target[`${propertyKey}_Query_parameters`];
            const pBody = target[`${propertyKey}_Body_parameters`];
            const pHeader = target[`${propertyKey}_Header_parameters`];

            descriptor.value = function (...args: any[]) {

                // Body
                let body = null;
                if (pBody) {
                    const bodyObj = args[pBody[0].parameterIndex];
                    if (typeof bodyObj === 'string') {
                        body = bodyObj;
                    } else {
                        body = JSON.stringify(bodyObj);
                    }
                }

                // Path
                let resUrl = '';
                if (typeof url === 'string') {
                    resUrl = url;
                }
                if (pPath) {
                    for (const k in pPath) {
                        if (pPath.hasOwnProperty(k)) {
                            resUrl = resUrl.replace('{' + pPath[k].key + '}', args[pPath[k].parameterIndex]);
                        }
                    }
                }

                // Query
                const search = new URLSearchParams();
                if (pQuery) {
                    pQuery
                        .filter(p => args[p.parameterIndex]) // filter out optional parameters
                        .forEach(p => {
                            const key = p.key;
                            let value = args[p.parameterIndex];
                            // if the value is a instance of Object, we stringify it
                            if (value instanceof Object) {
                                value = JSON.stringify(value);
                            }
                            search.set(encodeURIComponent(key), encodeURIComponent(value));
                        });
                }

                // Headers
                // set class default headers
                const headers = new AngularHeaders(this.getDefaultHeaders());
                // set method specific headers
                for (const k in descriptor.headers) {
                    if (descriptor.headers.hasOwnProperty(k)) {
                        headers.append(k, descriptor.headers[k]);
                    }
                }
                // set parameter specific headers
                if (pHeader) {
                    for (const k in pHeader) {
                        if (pHeader.hasOwnProperty(k)) {
                            headers.append(pHeader[k].key, args[pHeader[k].parameterIndex]);
                        }
                    }
                }

                if (headers.keys().length === 0) {
                    // Since no header specified, use json by default.
                    headers.append('Content-Type', 'application/json');
                }

                let overrideUrl = null;
                if (pUrl) {
                    overrideUrl = args[pUrl[0].parameterIndex] + resUrl;
                } else {
                    overrideUrl = this.getBaseUrl() + resUrl;
                }
                // Request options
                const options = new RequestOptions({
                    method,
                    url: overrideUrl,
                    headers,
                    body,
                    search
                });

                if (descriptor.responseContentType) {
                    options.responseType = descriptor.responseContentType;
                }

                const req = new Request(options);

                // intercept the request
                this.requestInterceptor(req);
                // make the request and store the observable for later transformation
                let observable: Observable<Response> = this.http.request(req);

                // transform the obserable in accordance to the @Produces decorator
                if (typeof descriptor.responseContentType === 'undefined' || descriptor.responseContentType === ResponseContentType.Json) {
                    observable = observable.map(res => {
                        try {
                            return res.json();
                        } catch (e) {
                            return res;
                        }
                    });
                }

                // intercept the response
                observable = this.responseInterceptor(observable);

                return observable;
            };

            return descriptor;
        };
    };
}

export class Resource<T> {
    public constructor( @Inject(HttpService) protected http: HttpService) {

    }

    protected getBaseUrl(): string {
        return null;
    }

    protected getDefaultHeaders(): Object {
        return null;
    }

    protected requestInterceptor(req: Request) {

    }

    protected responseInterceptor(res: Observable<any>): Observable<any> {
        return res;
    }

    @GET('/{id}')
    findOne( @Path('id') id: any): Observable<T> {
        return null;
    }

    @POST()
    save( @Body body: any): Observable<T> {
        return null;
    }

    @PUT('/{id}')
    update( @Path('id') id: any, @Body body: T): Observable<T> {
        return null;
    }

    @DELETE('/{id}')
    delete( @Path('id') id: any): Observable<T> {
        return null;
    }
}
