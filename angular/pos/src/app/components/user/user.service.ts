import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from 'src/app/app-constants';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
<<<<<<< HEAD
import { MatRipple } from '@angular/material/core';
=======
import { NumberSymbol } from '@angular/common';
>>>>>>> 85845f76450982ca50f37d0ced4ac7be71947481

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userId: number;

<<<<<<< HEAD
  userID: number;

  constructor(private http: HttpClient, private router:Router, private toast: ToastrService) { }
=======
  constructor(private http: HttpClient, private router: Router, private toast: ToastrService) { }
>>>>>>> 85845f76450982ca50f37d0ced4ac7be71947481

  isEditMode() {
    if (this.userId !== undefined) {
      return true;
    } else {
      return false;
    }
  }
  setId(id) {
    this.userId = id;
  }
  getUser() {
    return this.http.get(AppConstants.baseUsers + '/' + this.userId);
  }
  getUsers() {
    return this.http.get(AppConstants.baseUsers);
  }
<<<<<<< HEAD

  delete(dialog: MatDialog){
    return this.http.delete(AppConstants.baseUsers+'/'+this.userID).subscribe(data =>{
      this.toast.success('Usuário deletado com sucesso.');
      dialog.closeAll();
    },
    error => {
      this.toast.error('Erro ao deletar usuário.');
    });
  }

  createUser(user, dialog:MatDialog) {
=======
  updateUser(user, dialog: MatDialog) {
    return this.http.patch(AppConstants.baseUsers + '/' + this.userId, user).subscribe(data => {
      this.toast.success('Usuário atualizado com sucesso.');
      dialog.closeAll();
    },
      error => {
        this.toast.error('Erro ao atualizar usuário.');
      });
  }

  createUser(user, dialog: MatDialog) {
    this.setId(undefined);
>>>>>>> 85845f76450982ca50f37d0ced4ac7be71947481
    return this.http.post(AppConstants.baseUsers, user).subscribe(data => {
      this.toast.success('Cadastro realizado com sucesso.');
      dialog.closeAll();
    },
      error => {
        this.toast.error('Error ao realizar cadastro.');
      });
  }

  setUserDeleted(id: number){
    this.userID = id;
  }
}
