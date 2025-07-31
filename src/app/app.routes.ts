import { Routes } from '@angular/router';
import { Registration } from './Auth/registration/registration';
import { Login } from './Auth/login/login';
// import { StudentDashboard } from './student-dashboard/student-dashboard';
import { InstructorDashboard } from './instructor-dashboard/instructor-dashboard';
import { CreateNewCourse } from './instructor-dashboard/create-new-course/create-new-course';
// import { authGuard } from './guards/auth-guard';
import { Unauthorized } from './unauthorized/unauthorized';
import { InstructorMyCourseComponent } from './instructor-dashboard/instructor-my-course/instructor-my-course';
import { instructorGuard } from './guards/auth-guard';
import { studentGuard } from './guards/student-guard';
import { NotFound } from './not-found/not-found';
import { CourseDetails } from './course-details/course-details';
import { StudentDashboard } from './student-dashboard/student-dashboard';

export const routes: Routes = [
    { path: 'login', component: Login, },
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'registration', component: Registration },
    {path:'student-dashboard',component:StudentDashboard,canMatch:[studentGuard]},    
    // {path:'instructor-dashboard',component:InstructorDashboard},
      {
    path: 'instructor-dashboard',
    canMatch: [instructorGuard],
    component: InstructorDashboard, // The main dashboard layout (sidebar + navbar)
    children: [
      { path: 'create-new-course', component: CreateNewCourse },
      { path: 'my-courses', component: InstructorMyCourseComponent },
      
      // add other instructor routes here
    ]
  },
  {path:'course/:id',component:CourseDetails,canMatch:[studentGuard]},
    { path: 'unauthorized', component: Unauthorized },
{path:'**',component:NotFound}
];
