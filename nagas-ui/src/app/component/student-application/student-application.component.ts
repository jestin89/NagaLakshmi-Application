import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { StudentApplicationService } from 'src/app/services/student-application.service';

@Component({
  selector: 'app-student-application',
  templateUrl: './student-application.component.html',
  styleUrls: ['./student-application.component.css']
})
export class StudentApplicationComponent implements OnInit {
  studentForm: FormGroup;
  submitted = false;
  constructor(
    private formBuilder: FormBuilder,
    private service: StudentApplicationService
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.studentForm = this.formBuilder.group({
      id: [''],
      studentName: ['', Validators.required],
      registerNo: ['', Validators.required],
      education: ['', Validators.required],
      instituteName: ['', Validators.required],
      course: ['', Validators.required],
      department: ['', Validators.required],
      mobileNo: ['', Validators.required],
      emailId: ['', [Validators.required, Validators.email]],
      userId: [''],
      bonafide: ['', Validators.required]
    });
  }

  register() {
    this.submitted = true;
    if (this.studentForm.valid) {
      console.log('studentForm:', this.studentForm.value);
      this.service.register(this.studentForm.value).subscribe((data: any) => {
        console.log('Response:', data);
      }, (error) => {
        console.log('Error:', error);
      });
    }
  }

  get f() { return this.studentForm.controls; }

  onReset() {
    this.submitted = false;
    this.studentForm.reset();
  }

}
