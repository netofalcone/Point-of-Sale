import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from 'src/app/app-constants';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private router:Router, private toast: ToastrService) { }

  getUsers() {
    console.log()
    return this.http.get(AppConstants.baseUsers);
  }
  createUser(user, dialog:MatDialog) {
    return this.http.post(AppConstants.baseUsers, user).subscribe(data => {
      this.toast.success('Cadastro realizado com sucesso.');
      dialog.closeAll();
    },
    error => {
      this.toast.error('Error ao realizar cadastro.');
    });
  }
}
