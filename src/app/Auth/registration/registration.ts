import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { AuthService } from './auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  imports: [FormsModule,CommonModule],
  templateUrl: './registration.html',
  styleUrl: './registration.css'
})
export class Registration {
 user = {
    fullname: '',
    email: '',
    password: '',
    role: ''
  };
  errorMessage='';
constructor(private authService:AuthService,private router:Router){}
   onRegister(form: any) {
        this.user.role = this.user.role.toUpperCase();
  if (form.valid) {
    this.authService.registerUser(this.user).subscribe({
      next: (response) => {
        console.log('Registered:', response);
        this.router.navigate(['/login'],{replaceUrl:true})
      },
      error: (error) => {
        // Show error
        this.errorMessage = error.error.message || 'Registration failed!';

        // ❗ Auto-hide after 2 seconds
        setTimeout(() => {
          this.errorMessage = '';
        }, 2000);
      }
    });
  }
}
goToLogin(){
  this.router.navigate(['/login'],{replaceUrl:true})
}
}
