import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Student } from './student';
import { CommonModule } from '@angular/common';
import { CourseDetails } from '../course-details/course-details';
import { CourseModel } from './CourseModel/CourseModel';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-student-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './student-dashboard.html',
  styleUrl: './student-dashboard.css',
})
export class StudentDashboard {
  courses: any[] = [];
  purchasedCourseIds: number[] = [];
  email: string | null = null;
  showPopup: boolean = false;
  xyz: boolean = false;
  err: string = '';

  constructor(private router: Router, private studentService: Student) {}

  ngOnInit() {
    this.loadCoursesWithPurchaseStatus();
  }

  loadCoursesWithPurchaseStatus() {
    const token = localStorage.getItem('token');
    if (!token) return;

    const decoded: any = jwtDecode(token);
    const email = decoded?.sub;
    this.email = email;

    if (!email) return;

    this.studentService.getStudentByEmail(email).subscribe({
      next: (student: any) => {
        const studentId = student.user_id;

        // 1️⃣ Get Purchased Courses
        this.studentService.getStudById(studentId).subscribe({
          next: (purchasedCourses: any[]) => {
            this.purchasedCourseIds = purchasedCourses.map((c: any) => c.id);

            // 2️⃣ Get all courses
            this.studentService.getAllCourse().subscribe((allCourses: any[]) => {
              // 3️⃣ Mark purchased
              this.courses = allCourses.map((course: any) => ({
                ...course,
                isBought: this.purchasedCourseIds.includes(course.id)
              }));
            });
          },
          error: (err) => {
            console.error('Error fetching purchased courses:', err);
          }
        });
      },
      error: (err) => {
        console.error('Error fetching student by email:', err);
      }
    });
  }

  watchVideo(course: any) {
    const token = localStorage.getItem('token');
    if (!token) return;

    const decoded: any = jwtDecode(token);
    const email = decoded?.sub;

    if (!email) return;

    this.studentService.getStudentByEmail(email).subscribe({
      next: (student: any) => {
        const studentId = student.user_id;

        this.studentService.postCourseStudentId(course.id, studentId).subscribe({
          next: (res: any) => {
            this.showPopup = true;
            setTimeout(() => {
              this.showPopup = false;
              this.router.navigate(['/course', course.id]);
            }, 2000);
          },
          error: (err) => {
            console.error('❌ Enrollment failed:', err);
            if (err.error?.message) {
              course.isBought = true;
              this.xyz = true;
              this.err = err.error.message;

              setTimeout(() => {
                this.xyz = false;
                this.err = '';
              }, 2000);
            }
            this.showPopup = false;
          }
        });
      }
    });
  }

  onMyLearning() {
    this.router.navigate(['/my-learning']);
  }
addEarning(id: any, amount: any): void {
  this.studentService.addEarning(id, amount).subscribe({
    next: (data) => {
      console.log('Earning added successfully', data);
      // You can update UI or show success message here
    },
    error: (err) => {
      console.error('Error adding earning', err);
      // Optionally show error alert to user
    }
  });
}
categories(){
  this.router.navigate(['/categories']);
}
  onLogout() {
    localStorage.clear();
    this.router.navigate(['/login'], { replaceUrl: true });
  }
}

