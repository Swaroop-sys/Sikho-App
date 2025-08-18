import { CommonModule, SlicePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { CourseService } from '../services/course.service'; // adjust the path
import { InstructorService } from '../instructor-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-instructor-my-course',
  standalone: true,
  imports: [SlicePipe,CommonModule,FormsModule],
  templateUrl: './instructor-my-course.html',
  styleUrls: ['./instructor-my-course.css']
})
export class InstructorMyCourseComponent implements OnInit {
  courses: any[] = [];
  searchTerm: string = '';
// courses: Course[] = []; // all courses
filteredCourses: any[] = []; // result after search
  constructor(private router: Router, private courseService: InstructorService) {}

  ngOnInit(): void {
    this.fetchMyCourses();
      // this.filteredCourses = this.courses;

  }

  fetchMyCourses(): void {
    this.courseService.myCourse().subscribe({
      next: (response: any[]) => {
        this.courses = response;
      },
      error: (err) => {
        console.error('Failed to fetch courses', err);
      }
    });
  }
  filterCourses() {
  const term = this.searchTerm.toLowerCase();
  this.filteredCourses = this.courses.filter(course =>
    course.title.toLowerCase().includes(term) ||
    course.instructorName.toLowerCase().includes(term)
  );
}
onDelete(id:any){
  this.courseService.deleteCourseById(id).subscribe({
        next: (res) => {
          alert('Course deleted successfully!');
          this.ngOnInit();
        },
        error: (err) => {
          alert('Error deleting course: ' + err.error);
        }
      });
}
}
