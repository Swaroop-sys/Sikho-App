import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {
  
  
  constructor(private http:HttpClient){
  }

  createNewCourse(userData: any):Observable<any> {
    const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  });
      return this.http.post<any>( 'https://sikho-app-production.up.railway.app/api/course/add', userData,{headers});
    }
    // my-courses
    myCourse(): Observable<any> {
  const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.get<any>('https://sikho-app-production.up.railway.app/api/course/my-courses', { headers });
}

}
