import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CourseModel } from './CourseModel/CourseModel';

@Injectable({
  providedIn: 'root'
})
export class Student {
  token=localStorage.getItem('token');
  constructor(private http:HttpClient){}
  getUrl='https://sikho-app-production.up.railway.app/api/course/all-course';

 private selectedCourse: CourseModel | null = null;

 
getCourseById(id: number): Observable<any> {
     const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    
  });
  return this.http.get(`https://sikho-app-production.up.railway.app/api/course/get-by/${id}`,{headers});
}

 
  getAllCourse():Observable<any>{
     const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    
  });
    return this.http.get(this.getUrl,{headers});
  }
}
