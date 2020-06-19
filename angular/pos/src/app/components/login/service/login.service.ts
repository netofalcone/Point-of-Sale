import { AppConstants } from './../../../app-constants';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  login(user) {
    return this.http.post(AppConstants.baseLogin, JSON.stringify(user)).subscribe(data => {
      var token = JSON.parse(JSON.stringify(data)).Authorization.split(' ')[1];
      localStorage.setItem("token",token);
    },
    error =>{
      console.error("Erro ao realizar Login");
    });
  }
}
