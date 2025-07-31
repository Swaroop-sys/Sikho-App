import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../Auth/registration/auth-service';
import { Login } from '../Auth/login/login';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { CustomJwtPayload } from '../Jswtpayload/JwtPayload';

@Component({
  selector: 'app-instructor-dashboard',
  imports: [RouterModule],
  templateUrl: './instructor-dashboard.html',
  styleUrl: './instructor-dashboard.css'
})
export class InstructorDashboard implements OnInit{
  constructor(private router:Router){}
 decoded: CustomJwtPayload | null = null;

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.decoded = jwtDecode<CustomJwtPayload>(token);
    }
  }
 goToCreateCourse() {
    this.router.navigate(["instructor-dashboard/create-new-course"]);
  }
  gotoMyCourse(){
    this.router.navigate(["instructor-dashboard/my-courses"]);
  }
  logout(): void {
 
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.clear();
    this.router.navigate(['login']); 

}
  

}
