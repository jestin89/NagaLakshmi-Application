import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { StudentApplicationComponent } from './component/student-application/student-application.component';
import { FooterComponent } from './main/footer/footer.component';
import { HeaderComponent } from './main/header/header.component';
import { LandingPageComponent } from './main/landing-page/landing-page.component';
import { LayoutComponent } from './main/layout/layout.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from './services/login.service';
import { StudentApplicationService } from './services/student-application.service';
import {HttpClientModule} from '@angular/common/http';
import { SubscriberDashboardComponent } from './component/subscriber-dashboard/subscriber-dashboard.component';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { StudentDashboardComponent } from './component/student-dashboard/student-dashboard.component';
import { DialogMessageComponent } from './component/shared/dialog-message/dialog-message.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    StudentApplicationComponent,
    LayoutComponent,
    LandingPageComponent,
    FooterComponent,
    HeaderComponent,
    SubscriberDashboardComponent,
    AdminDashboardComponent,
    StudentDashboardComponent,
    DialogMessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [LoginService,StudentApplicationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
