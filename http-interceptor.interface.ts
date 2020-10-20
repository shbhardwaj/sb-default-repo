import { RequestOptionsArgs, Response } from '@angular/http';

export interface IHttpInterceptor {
    onRequest?: (requestOptions: RequestOptionsArgs) => RequestOptionsArgs;
    onResponse?: (response: Response) => Response;
    onResponseError?: (error: Response) => Response;
}
