import { Injectable } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageServiceService} from "./token-storage-service.service";
import {Observable} from "rxjs";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthInetrceptorService implements HttpInterceptor {

  constructor(private tokenService: TokenStorageServiceService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest = req;
    const token = this.tokenService.getToken();
    if (token != null) {
      authRequest = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, token)});
    }

    return next.handle(authRequest);
  }


}

export const authInetrceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInetrceptorService, multiple: true}
];
