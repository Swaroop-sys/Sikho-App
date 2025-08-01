import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from './student';
import { CommonModule } from '@angular/common';
import { CourseDetails } from '../course-details/course-details';
import { CourseModel } from './CourseModel/CourseModel';
// import { SafePipe } from './safe-pipe';

@Component({
  selector: 'app-student-dashboard',
  imports: [CommonModule],
  templateUrl: './student-dashboard.html',
  styleUrl: './student-dashboard.css'
})
export class StudentDashboard {
courses:any[]=[];
courses2: CourseDetails[] = []; 
constructor(private router:Router,private studentService:Student,private courseService:Student){}
ngOnInit() {
  this.getAllCourse();
}

getAllCourse() {
  this.studentService.getAllCourse().subscribe((res: any[]) => {
    this.courses = res;
  });
}
selectedVideoUrl: string | null = null;

watchVideo(course: any) {
  this.router.navigate(['/course', course.id]);
}

onLogout(){
  console.log("Button Logout CLicked");
  
  localStorage.clear();
  this.router.navigate(['/login'],{replaceUrl:true});
}

}
