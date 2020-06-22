import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';


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
    localStorage.clear();
  }
  criarFormularioLogin() {
    this.formularioLogin = this.fb.group({
      email: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])]
    });
  }
  login() {
    this.loginService.login(this.formularioLogin.value);
  }


}
