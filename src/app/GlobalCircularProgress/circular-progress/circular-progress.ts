import { Component } from '@angular/core';
import { LoaderService } from './LoaderService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-circular-progress',
  imports: [CommonModule],
  templateUrl: './circular-progress.html',
  styleUrl: './circular-progress.css'
})
export class CircularProgress {
  constructor(public loader: LoaderService) {}

}
