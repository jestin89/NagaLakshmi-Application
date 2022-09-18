import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { StudentApplicationComponent } from './component/student-application/student-application.component';
import { StudentDashboardComponent } from './component/student-dashboard/student-dashboard.component';
import { SubscriberDashboardComponent } from './component/subscriber-dashboard/subscriber-dashboard.component';
import { LayoutComponent } from './main/layout/layout.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  /*Temporary path start*/
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'studentapplication',
    component: StudentApplicationComponent,
  },
  {
    path: 'subscriberDashboard',
    component: SubscriberDashboardComponent,
  },
  {
    path: 'adminDashboard',
    component: AdminDashboardComponent,
  },
  {
    path: 'studentDashboard',
    component:StudentDashboardComponent,
  },
  /*Temporary path end*/
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'register',
        component: RegisterComponent,
      },
      {
        path: 'studentapplication',
        component: StudentApplicationComponent,
      },
    ]

  }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
