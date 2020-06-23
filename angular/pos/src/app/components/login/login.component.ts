import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';
import { FormBuilder, FormGroup, Validators, FormControl  } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  constructor(private loginService: LoginService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.createForm();
    if (localStorage.getItem('token') !== null &&
    localStorage.getItem('token').toString().trim() !== null) {
      this.loginService.goToHome();
    }
  }
  createForm() {
    this.formLogin = this.fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }
  login() {
    localStorage.clear();
    this.loginService.login(this.formLogin.value);
  }
  getValidation = (controlName: string, errorName: string) =>{
    if(this.inputTouched(controlName)){
      return this.formLogin.controls[controlName].hasError(errorName);
    }
    return false;
  }
  inputTouched(field){
    return this.formLogin.get(field).touched;
  }

}
