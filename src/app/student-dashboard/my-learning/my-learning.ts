import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { jwtDecode } from 'jwt-decode';
import { CommonModule, SlicePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-learning',
  imports: [SlicePipe,CommonModule],
  templateUrl: './my-learning.html',
  styleUrl: './my-learning.css'
})
export class MyLearning implements OnInit{
    token: string | null | undefined;
  decoded: any;
  email: string | null = null;
constructor(private studentService:Student,private router:Router){}
  ngOnInit(): void {
    this.getCourse();
  }
  courses: any[] = [];

getCourse() {
  const token = localStorage.getItem('token');
  if (token) {
    const decoded: any = jwtDecode(token);
    const email = decoded?.sub;

    if (email) {
      this.email = email;

      this.studentService.getStudentByEmail(this.email).subscribe({
        next: (student: any) => {
          const studentId = student.user_id;

          this.studentService.getStudById(studentId).subscribe({
            next: (res: any[]) => {
              this.courses = res;
              console.log("Student's courses:", this.courses);
            },
            error: (err) => {
              console.error('Error fetching student courses:', err);
            },
          });
        },
        error: (err) => {
          console.error('Error fetching student by email:', err);
        },
      });
    } else {
      console.warn('Email not found in token');
    }
  } else {
    console.warn('No token found');
  }
}
onStart(id:any){
  this.router.navigate(['/course',id])
}
}
