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
  getUrl='http://localhost:8080/api/course/all-course';

 private selectedCourse: CourseModel | null = null;

 
getCourseById(id: number): Observable<any> {
     const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    
  });
  return this.http.get(`http://localhost:8080/api/course/get-by/${id}`,{headers});
}

postCourseStudentId(courseId: any, studentId: any): Observable<any> {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`
  });

  const body = {
    studentId: studentId,
    courseId: courseId
  };

  return this.http.post('http://localhost:8080/api/student-courses/add-stud-course', body, { headers });
}
getStudentByEmail(email:any){
const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`
  });


  return this.http.get(`http://localhost:8080/api/auth/stud-email/${email}`, { headers });
}
  getAllCourse():Observable<any>{
     const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    
  });
    return this.http.get(this.getUrl,{headers});
  }
  getStudById(id:any):Observable<any>{
     const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    
  });
return this.http.get(`http://localhost:8080/api/student-courses/get-course-student/${id}`,{headers})
  }



  //Add Earning Api
 addEarning(instructorId: any, amount: any) {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    'Content-Type': 'application/json'
  });

  return this.http.post(
    'http://localhost:8080/api/earning/add-earning',
    { instructorId, amount },
    { headers }
  );
}
getByCategory(categoryName:string):Observable<any[]>{
   const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.token}`,
    'Content-Type': 'application/json'
  });
return this.http.get<any[]>(`http://localhost:8080/api/course/get-by-category/${categoryName}`,{headers});
}
}
