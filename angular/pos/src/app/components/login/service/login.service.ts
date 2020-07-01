import { AppConstants } from './../../../app-constants';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient, private router:Router, private toast: ToastrService) { }

  login(user) {
    return this.http.post(AppConstants.baseLogin, JSON.stringify(user)).subscribe(data => {
      var token = JSON.parse(JSON.stringify(data)).Authorization;
      localStorage.setItem('token', token);
      this.goToHome();
    },
    error => {
      this.toast.error('Email ou senha inválidos.', 'Falha ao realizar login');
    });
  }
  public goToHome(): void {
    this.router.navigate(['']);
  }
  validLogin(){
    if (localStorage.getItem('token') === null ) {
      this.router.navigate(['/login']);
  }
  }
  logOut(){
    localStorage.clear();
    this.validLogin();
  }
}
