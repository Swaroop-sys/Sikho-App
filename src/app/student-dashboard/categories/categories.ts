import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories',
  imports: [],
  templateUrl: './categories.html',
  styleUrl: './categories.css'
})
export class Categories {
constructor(private router: Router) {}

goToCategory(categoryName: string) {
  this.router.navigate(['deatiled-categories', categoryName]);
}
}
