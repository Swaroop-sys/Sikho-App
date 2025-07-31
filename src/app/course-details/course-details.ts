import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../student-dashboard/student';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CourseModel } from '../student-dashboard/CourseModel/CourseModel';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-course-details',
  imports: [FormsModule,CommonModule],
  templateUrl: './course-details.html',
  styleUrl: './course-details.css'
})
export class CourseDetails{
  course: any;
  safeVideoUrl!: SafeResourceUrl;

  constructor(
    private route: ActivatedRoute,
    private courseService: Student,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    const courseId = this.route.snapshot.paramMap.get('id');
    if (courseId) {
      this.courseService.getCourseById(+courseId).subscribe(data => {
        this.course = data;
        this.safeVideoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.course.videoUrl);
      });
    }
  }
}
