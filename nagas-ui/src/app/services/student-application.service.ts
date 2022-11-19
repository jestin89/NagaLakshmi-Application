import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentApplicationService {

  apiUrl = environment.apiUrl;
  constructor(
    private http: HttpClient
  ) { }

  register(value: any) {
    return this.http.post(this.apiUrl + '/application/save', value)
  }

  getStudentDetailsById(userId: number) {
    return this.http.get(this.apiUrl + '/application/getbyuserid/' + userId)
  }

  getAllStudentDetails() {
    return this.http.get(this.apiUrl + '/application/getAllStudentDetails')
  }
}
