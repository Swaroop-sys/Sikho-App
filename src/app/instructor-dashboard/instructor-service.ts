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
      return this.http.post<any>( 'http://localhost:8080/api/course/add', userData,{headers});
    }
    // my-courses
    myCourse(): Observable<any> {
  const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.get<any>('http://localhost:8080/api/course/my-courses', { headers });
}
myEarning(id:any):Observable<any>{
  const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });
return this.http.get(`http://localhost:8080/api/earning/total-earning/${id}`,{headers})
}
getUserByEmail(email:any){
const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });
return this.http.get(`http://localhost:8080/api/auth/stud-email/${email}`,{headers})
}
deleteCourseById(id: number): Observable<string> {
  const token = localStorage.getItem('token') || '';
  
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.delete<string>(
    `http://localhost:8080/api/course/delete-course-by-id/${id}`,
    { headers }
  );
}

}
