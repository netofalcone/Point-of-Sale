import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';
import { FormBuilder, FormGroup, Validators, FormControl  } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formularioLogin: FormGroup;
  constructor(private loginService: LoginService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.criarFormularioLogin();
    if (localStorage.getItem('token') !== null &&
    localStorage.getItem('token').toString().trim() !== null) {
      this.loginService.goToHome();
    }
  }
  criarFormularioLogin() {
    this.formularioLogin = this.fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }
  login() {
    localStorage.clear();
    this.loginService.login(this.formularioLogin.value);
  }
  getValidation = (controlName: string, errorName: string) =>{
    if(this.inputTouched(controlName)){
      return this.formularioLogin.controls[controlName].hasError(errorName);
    }
    return false;
  }
  inputTouched(field){
    return this.formularioLogin.get(field).touched;
  }

}
