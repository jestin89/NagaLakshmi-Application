import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { DialogMessageComponent } from './component/shared/dialog-message/dialog-message.component';
import { StudentApplicationComponent } from './component/student-application/student-application.component';
import { StudentDashboardComponent } from './component/student-dashboard/student-dashboard.component';
import { SubscriberDashboardComponent } from './component/subscriber-dashboard/subscriber-dashboard.component';
import { LandingPageComponent } from './main/landing-page/landing-page.component';
import { LayoutComponent } from './main/layout/layout.component';

const routes: Routes = [ 
  { path: '', redirectTo: "/home", pathMatch: "full" },
  {
    path: 'home', component: LayoutComponent,
    children: [
      { path: '', component: LandingPageComponent },
      { path: 'studentapplication/:id', component: StudentApplicationComponent },
      { path: 'studentDashboard/:id', component: StudentDashboardComponent },
      { path: 'subscriberDashboard', component: SubscriberDashboardComponent },
      { path: 'adminDashboard', component: AdminDashboardComponent }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dialogMessage', component: DialogMessageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
