import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Application, ApplicationResponse } from 'src/app/model/applicationResponse';
import { LoginResponse } from 'src/app/model/loginResponse';
import { StudentApplicationService } from 'src/app/services/student-application.service';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css']
})
export class StudentDashboardComponent implements OnInit {
  sub: any;
  id: number;
  application = new Application();
  studentEnable: boolean = false;
  showTable:boolean = false;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: StudentApplicationService
  ) { }

  ngOnInit(): void {
    this.studentEnable = true;
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];      
    });
    console.log(this.id);
    if (this.id) {
      this.service.getStudentDetailsById(this.id).subscribe((response: ApplicationResponse) => {
        console.log('REsponse:', response);
        if (response != null && response.request != null && response.request) {
          this.studentEnable = false;
          this.showTable = true;
          this.application = response.request;
        }
      });
    }
  }

  studentApplication() {
    this.router.navigateByUrl(`/home/studentapplication/${this.id}`);
  }
}
