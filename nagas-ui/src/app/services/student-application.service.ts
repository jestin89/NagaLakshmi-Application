import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentApplicationService {  

  apiUrl = environment.apiUrl;
  constructor(
    private http: HttpClient
  ) { }

  register(value: any): Observable<string> {
    return this.http.post(this.apiUrl + '/application/save', value, {
      responseType: 'text'
    });
  }

  getStudentDetailsById(userId: number) {
    return this.http.get(this.apiUrl + '/application/getByUserId/' + userId)
  }

  getFile(fileId: number): Observable<Blob> {
    return this.http.get(this.apiUrl + '/application/downloadFile/' + fileId, {
      responseType: 'blob'
    });
  } 

  getAllStudentDetails() {
    return this.http.get(this.apiUrl + '/application/getAllStudentDetails')
  }

  uploadFile(file: File, id: number): Observable<string>  {    
    const formdata: FormData = new FormData();      
    formdata.append('file', file);  
    let url = this.apiUrl + "/application/uploadImage/" + id ;  
    return this.http.post(url , formdata, {
      responseType: 'text'
    }); 
  } 
 
}
