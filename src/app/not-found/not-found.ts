import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-found',
  imports: [CommonModule],
  templateUrl: './not-found.html',
  styleUrl: './not-found.css'
})
export class NotFound {
 tipShown = false;
  randomStudyTip = '';
  tips = [
    'Take breaks! Study 25 mins, rest 5 mins — Pomodoro works wonders.',
    'Teach someone else what you learned — it helps you master it!',
    'Drink water. Hydrated brain = Happy brain.',
    'Create a study playlist. Mozart or lo-fi beats help with focus.',
    'Turn off notifications. Your brain will thank you later!',
    'Study in short sprints, not marathons!'
  ];

  constructor(private router: Router) {}

  goHome() {
    this.router.navigate(['/']);
  }

  goBack() {
    window.history.back();
  }

  randomTip() {
    this.randomStudyTip = this.tips[Math.floor(Math.random() * this.tips.length)];
    this.tipShown = true;
  }
}
