import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  constructor(
    private formBuilder: FormBuilder,
    private service: LoginService
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.registerForm = this.formBuilder.group({
      "id": [''],
      "userName": ['', Validators.required],
      "password": ['', Validators.required],
      "confirmPassword": ['', Validators.required],
      "emailId": ['', [Validators.required, Validators.email]],
      "mobileNo": ['', Validators.required],
      "role": ['', Validators.required]
    });
  }

  register() {
    this.submitted = true;
    if (this.registerForm.valid) {
      console.log('registerForm:', this.registerForm.value);
      this.service.register(this.registerForm.value).subscribe((data: any) => {
        console.log('REsponse:', data);
      }, (error) => {
        console.log('error:', error);
      });
    }
  }

  get f() { return this.registerForm.controls; }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
