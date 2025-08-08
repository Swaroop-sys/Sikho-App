import { Component, OnInit } from '@angular/core';
import { InstructorService } from '../instructor-service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-earning',
  templateUrl: './earning.html',
  styleUrls: ['./earning.css'] // correct spelling!
})
export class Earning implements OnInit {  // <-- implements OnInit
  totalEarning: number = 0;

  constructor(private instructorService: InstructorService) {}

  ngOnInit(): void {
    console.log('ngOnInit called'); // debug
    const token = localStorage.getItem('token');
    if (token) {
      const decoded: any = jwtDecode(token);
      const email = decoded?.sub;
      console.log('Decoded email:', email); // debug

      if (email) {
        this.instructorService.getUserByEmail(email).subscribe({
          next: (user: any) => {
            console.log('User response:', user); // debug
            const userId = user?.user_id;
            if (userId) {
              this.instructorService.myEarning(userId).subscribe({
                next: (earning: any) => {
                  console.log('Earning from API:', earning); // debug
                  this.totalEarning = parseFloat(earning);
                },
                error: err => console.error('Error fetching earnings:', err)
              });
            }
          },
          error: err => console.error('Error fetching user by email:', err)
        });
      }
    }
  }
}
