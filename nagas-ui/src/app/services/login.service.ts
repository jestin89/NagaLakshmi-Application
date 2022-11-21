import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {  

  apiUrl = environment.apiUrl;
  constructor(
    private http: HttpClient
  ) { }

  login(value: any) {
    return this.http.post(this.apiUrl + '/login', value);
  }

  register(value: any): Observable<string> {
    return this.http.post(this.apiUrl + '/register', value, { responseType: 'text' });
  }

  getAll() {
    return this.http.get(this.apiUrl + '/getAllDetails');
  }

}

