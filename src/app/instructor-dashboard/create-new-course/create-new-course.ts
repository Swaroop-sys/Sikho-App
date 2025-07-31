import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InstructorService } from '../instructor-service';

@Component({
  selector: 'app-create-new-course',
  imports: [CommonModule,FormsModule],
  templateUrl: './create-new-course.html',
  styleUrl: './create-new-course.css'
})
export class CreateNewCourse {
 course = {
    title: '',
    description: '',
    category: '',
    thumbnailUrl: '',
    videoUrl: '',
    price: null
  };
  errorMessage: any;
constructor(private instructorService:InstructorService){}

  onSubmit() {
        this.instructorService.createNewCourse(this.course).subscribe({
      next: (response) => {
        console.log('Course Added:', response);
        alert('Course Added Successful!');
      },
      error: (error) => {
        // Show error
        this.errorMessage = error.error.message || 'Course Not Added';

        // ❗ Auto-hide after 2 seconds
        setTimeout(() => {
          this.errorMessage = '';
        }, 2000);
      }
    });
  }
}
