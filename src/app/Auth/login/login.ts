import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../registration/auth-service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [CommonModule,FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
 loginData = {
    email: '',
    password: ''
  };
isLoading = false;
  errorMessage: string = '';
constructor(private authService:AuthService,private router:Router){}
 onLogin(form: any) {
  if (form.valid) {
      this.isLoading = true;
    this.authService.loginUser(this.loginData).subscribe({
      next: (response) => {
        console.log('Login', response);
        
        
        
        localStorage.setItem("token", response.token); // ✅ Will not error now
        localStorage.setItem("role", response.role);
        // if(response.role == 'STUDENT'){
        //   this.router.navigateByUrl('/student-dashboard');
        //    this.isLoading = false;

        // }
        // else{
        //   this.router.navigateByUrl('/instructor-dashboard');
        //    this.isLoading = false;
        // }
        if(response.role=='STUDENT'){
           setTimeout(() => {
        this.router.navigate(['/student-dashboard']);
        this.isLoading = false;
      }, 2000); // Replace with real API call
    }
    else{
      setTimeout(() => {
        this.router.navigate(['/instructor-dashboard']);
        this.isLoading = false;
      }, 2000);
    }
        
      },
      error: (error) => {
        this.isLoading = false;
        
        this.errorMessage = error.error.message || 'Login failed!';

        // ❗ Auto-hide after 2 seconds
        setTimeout(() => {
          this.errorMessage = '';
        }, 2000);
      }
    });
  }
}
logout(): void {
 
    localStorage.removeItem('token');
    localStorage.removeItem('role'); 
}
   goToRegister() {
    this.router.navigate(['/registration'],{replaceUrl:true});
  }
}
