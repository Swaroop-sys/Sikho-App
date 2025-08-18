import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Student } from './student';
import { CommonModule } from '@angular/common';
import { CourseDetails } from '../course-details/course-details';
import { CourseModel } from './CourseModel/CourseModel';
import { jwtDecode } from 'jwt-decode';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule,FormsModule],
  templateUrl: './student-dashboard.html',
  styleUrl: './student-dashboard.css',
})
export class StudentDashboard {
  courses: any[] = [];
  allCourses: any[] = []; // Master copy of all courses
  purchasedCourseIds: number[] = [];
  
  email: string | null = null;
  showPopup = false;
  xyz = false;
  err = '';
  name?: string;
  selectedCategory: string = '';
  noCoursesMessage: string='';

  constructor(private router: Router, private studentService: Student) {
    const token: any = localStorage.getItem('token');
    const d: any = jwtDecode(token);
    this.name = d?.name;
  }

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

        this.studentService.getStudById(studentId).subscribe({
          next: (purchasedCourses: any[]) => {
            this.purchasedCourseIds = purchasedCourses.map((c: any) => c.id);

            this.studentService.getAllCourse().subscribe((allCourses: any[]) => {
              this.allCourses = allCourses.map(course => ({
                ...course,
                isBought: this.purchasedCourseIds.includes(course.id)
              }));
              this.courses = [...this.allCourses]; // Start with all
            });
          },
          error: (err) => console.error('Error fetching purchased courses:', err)
        });
      },
      error: (err) => console.error('Error fetching student by email:', err)
    });
  }

  onCategoryChange() {
    if (!this.selectedCategory) {
      this.courses = [...this.allCourses];
    } else {
      this.courses = this.allCourses.filter(c => c.category === this.selectedCategory);
    }
    if (this.courses.length === 0) {
    this.noCoursesMessage = `There are no courses available for ${this.selectedCategory || 'this category'}.`;
  } else {
    this.noCoursesMessage = '';
  }
    console.log(`Showing ${this.courses.length} courses for category: ${this.selectedCategory || 'All'}`);
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
          next: () => {
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
      next: (data) => console.log('Earning added successfully', data),
      error: (err) => console.error('Error adding earning', err)
    });
  }

  categories() {
    this.router.navigate(['/categories']);
  }

  onLogout() {
    localStorage.clear();
    this.router.navigate(['/login'], { replaceUrl: true });
  }
}

