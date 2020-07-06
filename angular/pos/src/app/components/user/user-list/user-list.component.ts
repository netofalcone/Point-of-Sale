import { ModalDeleteComponent } from './../modal-delete/modal-delete.component';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { UserEditComponent } from '../user-edit/user-edit.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: any;

  constructor(private userService: UserService, private router: Router, public dialog: MatDialog) {
    this.userService.getUsers().subscribe(result => {
      this.users = result;
    });
  }

  ngOnInit(): void { }

  public goToHome(): void {
    this.router.navigate(['']);
  }

  openDialog(){
    this.dialog.open(UserEditComponent);
  }

  openDeleteDialog(id: number){
    this.userService.setUserDeleted(id);
    this.dialog.open(ModalDeleteComponent);
  }
}
