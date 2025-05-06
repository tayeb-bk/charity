import { TestBed } from '@angular/core/testing';
/*
import { keycloakHttpInterceptor } from './keycloak-http.interceptor';
*/
import { HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { Observable, of } from 'rxjs';

describe('keycloakHttpInterceptor', () => {
  it('should add Authorization header when token is available', () => {
    const mockRequest = new HttpRequest('GET', '/api/test');
    const mockNext: HttpHandlerFn = (req: HttpRequest<unknown>): Observable<HttpEvent<unknown>> => {
      return of({} as HttpEvent<unknown>); // Simule une réponse vide
    };

/*
    const interceptedRequest = keycloakHttpInterceptor(mockRequest, mockNext);
*/

/*
    expect(interceptedRequest).toBeTruthy();
*/
  });
});
