import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Registration } from "./Auth/registration/registration";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Registration,FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('elearning-frontend');
}
