// import { Injectable } from '@angular/core';
// import {
//   HttpEvent,
//   HttpInterceptor,
//   HttpHandler,
//   HttpRequest,
//   HttpErrorResponse
// } from '@angular/common/http';
// import { Observable, throwError } from 'rxjs';
// import { catchError } from 'rxjs/operators';
// import { ToastrService } from 'ngx-toastr'; // Optional for toast messages

// @Injectable()
// export class HttpErrorInterceptor implements HttpInterceptor {

//   constructor(private toastr: ToastrService) {}

//   intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
//     return next.handle(request).pipe(
//       catchError((error: HttpErrorResponse) => {
//         let errorMsg = 'Something went wrong!';

//         if (error.error && error.error.message) {
//           errorMsg = error.error.message;
//         }

//         // Display toast message
//         this.toastr.error(errorMsg, 'Error');

//         // Optionally log or send to analytics
//         console.error('HTTP Error:', error);

//         return throwError(() => new Error(errorMsg));
//       })
//     );
//   }
// }
