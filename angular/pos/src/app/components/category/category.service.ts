import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AppConstants } from 'src/app/app-constants';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  userId: number;

  constructor(private http: HttpClient, private router: Router, private toast: ToastrService) { }

  setId(id) {
    this.userId = id;
  }

  createUser(user, dialog: MatDialog) {
    this.setId(undefined);
    return this.http.post(AppConstants.baseCategories, user).subscribe(data => {
      this.toast.success('Cadastro realizado com sucesso.');
      dialog.closeAll();
    },
      error => {
        this.toast.error('Error ao realizar cadastro.');
      });
  }


}
