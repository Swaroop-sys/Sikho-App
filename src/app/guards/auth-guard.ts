import { inject } from '@angular/core';
import { CanMatchFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
// import jwtDecode from 'jwt-decode';

interface JwtPayload {
  role: string;
  exp: number;
}

export const instructorGuard: CanMatchFn = () => {
  const router = inject(Router);
  const token = localStorage.getItem('token');

  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  try {
    const decoded: JwtPayload = jwtDecode(token);
    if (decoded.role !== 'INSTRUCTOR') {
      router.navigate(['/unauthorized']);
      return false;
    }
    return true;
  } catch (e) {
    router.navigate(['/login']);
    return false;
  }
};
