import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { StudentApplicationComponent } from './component/student-application/student-application.component';
import { LayoutComponent } from './main/layout/layout.component';

const routes: Routes = [
  {
    path:'',
    component:LayoutComponent,
    children:[
      {
        path:'login',
        component:LoginComponent,
      },
      {
        path:'register',
        component:RegisterComponent,
      },
      {
        path:'studentapplication',
        component:StudentApplicationComponent,
      },
    ]
    
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
