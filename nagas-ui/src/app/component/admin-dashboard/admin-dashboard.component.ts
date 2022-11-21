import { Component, OnInit } from '@angular/core';
import { Admin, AdminResponse } from 'src/app/model/adminResponse';
import { Application } from 'src/app/model/applicationResponse';
import { LoginService } from 'src/app/services/login.service';
import { StudentApplicationService } from 'src/app/services/student-application.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  studentApplication: Application[] = [];
  student: Admin[];
  subscriber: Admin[]
  constructor(
    private service: StudentApplicationService,
    private loginService:LoginService
  ) { }

  ngOnInit(): void {
    this.loginService.getAll().subscribe((response: AdminResponse) => {
      console.log('response:',response);
      if (response) {
        this.student = response.student;
        this.subscriber = response.subscriber;
      }

    });
    this.service.getAllStudentDetails().subscribe((response: Application[]) => {
      if (response) {
        this.studentApplication = response;
      }
    });
  }
}


