import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginResponse } from 'src/app/model/loginResponse';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted = false;
  constructor(
    private formBuilder: FormBuilder,
    private service: LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.loginForm = this.formBuilder.group({
      "userName": ['', [Validators.required, Validators.email]],
      "password": ['', Validators.required]
    });
  }

  login() {
    this.submitted = true;
    if (this.loginForm.valid) {
      console.log('LoginForm:', this.loginForm.value);
      this.service.login(this.loginForm.value).subscribe((data: LoginResponse) => {
        console.log('Response:', data);
        console.log('Response:', data.id);
        if (data != null) {
          if (data.role === 'Student') {            
            this.router.navigateByUrl(`/home/studentDashboard/${data.id}`);    
          }else if (data.role === 'Subscriber') {
            this.router.navigateByUrl('/home/subscriberDashboard');
          }else if (data.role === 'Admin') {
            this.router.navigateByUrl('/home/adminDashboard');
          }
        }
      }, (error) => {
        console.log('Error:', error);
      });
    }
  }

  get f() { return this.loginForm.controls; }

  onReset() {
    this.submitted = false;
    this.loginForm.reset();
  }


}
