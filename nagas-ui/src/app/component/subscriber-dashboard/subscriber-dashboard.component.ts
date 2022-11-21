import { Component, OnInit } from '@angular/core';
import { Application } from 'src/app/model/applicationResponse';
import { StudentApplicationService } from 'src/app/services/student-application.service';

@Component({
  selector: 'app-subscriber-dashboard',
  templateUrl: './subscriber-dashboard.component.html',
  styleUrls: ['./subscriber-dashboard.component.css']
})
export class SubscriberDashboardComponent implements OnInit {
  student: Application[] = [];
  constructor(
    private service: StudentApplicationService
  ) { }

  ngOnInit(): void {
    this.service.getAllStudentDetails().subscribe((response: Application[]) => {
      console.log('REEEE:',response);
      if (response) {
        this.student = response;
      }
    });
  }

}
