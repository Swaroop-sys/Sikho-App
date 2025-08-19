import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiConfigService } from '../../ApiConfigService/api-config-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient,private apiConfig:ApiConfigService) {}
 registerUser(userData: any) {
    return this.http.post(`${this.apiConfig}/api/auth/register`, userData);
  }
  loginUser(userData: any):Observable<any> {
    return this.http.post<any>(`${this.apiConfig}/api/auth/login`, userData);
  }
}
