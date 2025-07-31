import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {}
 registerUser(userData: any) {
    return this.http.post('https://sikho-app-production.up.railway.app/api/auth/register', userData);
  }
  loginUser(userData: any):Observable<any> {
    return this.http.post<any>('https://sikho-app-production.up.railway.app/api/auth/login', userData);
  }
}
