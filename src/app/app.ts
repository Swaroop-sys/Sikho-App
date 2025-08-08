import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Registration } from "./Auth/registration/registration";
import { FormsModule } from '@angular/forms';
import { CircularProgress } from "./GlobalCircularProgress/circular-progress/circular-progress";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Registration, FormsModule, CircularProgress],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('elearning-frontend');
}
