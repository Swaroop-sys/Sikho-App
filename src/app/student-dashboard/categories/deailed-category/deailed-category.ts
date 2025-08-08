import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../../student';
import { CommonModule, SlicePipe } from '@angular/common';

@Component({
  selector: 'app-deailed-category',
  imports: [SlicePipe,CommonModule],
  templateUrl: './deailed-category.html',
  styleUrl: './deailed-category.css'
})
export class DeailedCategory implements OnInit{
    constructor(private route: ActivatedRoute,private student:Student) {}
  courses: any[] = [];

  ngOnInit(): void {
    const categoryName = decodeURIComponent(this.route.snapshot.paramMap.get('categoryName') || '');

    this.student.getByCategory(categoryName).subscribe({
      next: (data) => {
        this.courses = data;
      },
      error: (err) => {
        console.error('Error fetching courses:', err);
      }
    });
  }

}
