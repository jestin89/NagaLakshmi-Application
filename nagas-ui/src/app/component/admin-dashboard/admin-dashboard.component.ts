import { Component, OnInit } from '@angular/core';
import { Application } from 'src/app/model/applicationResponse';
import { StudentApplicationService } from 'src/app/services/student-application.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  student: Application[] = [];
  constructor(
    private service: StudentApplicationService
  ) { }

  ngOnInit(): void {
    this.service.getAllStudentDetails().subscribe((response: Application[]) => {
      if (response) {
        this.student = response;
      }
    });
  }
}


