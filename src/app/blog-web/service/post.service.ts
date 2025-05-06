import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const BASIC_URL = 'http://localhost:8020';
@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }


  createNewPost(data: any): Observable<any> {
    return this.http.post<any>(BASIC_URL +'api/posts', data);
  }
}
