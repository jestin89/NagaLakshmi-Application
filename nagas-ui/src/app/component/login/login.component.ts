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
        if (data != null) {
          if (data.role === 'Student') {
            this.router.navigateByUrl('/studentDashboard');
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
