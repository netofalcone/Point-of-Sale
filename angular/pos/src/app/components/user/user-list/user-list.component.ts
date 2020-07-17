
import { AppConstants } from './../../../app-constants';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { UserEditComponent } from '../user-edit/user-edit.component';
import {UserViewModalComponent} from '../user-view-modal/user-view-modal.component';
import {ModalDeleteComponent} from '../../../shared/modal-delete/modal-delete.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: any;
  url: string;
  fields: string [];

  constructor(private userService: UserService, private router: Router, public dialog: MatDialog) {
    this.fetchUsers();
    this.url = AppConstants.baseUsers;
    this.fields = ['name'];
  }

  ngOnInit(): void {
  }

  public goToHome(): void {
    this.router.navigate(['']);
  }

  createUser() {
    this.userService.setId(undefined);
    const dialogRef = this.dialog.open(UserEditComponent);
    this.updateList(dialogRef);
  }

  editUser(id: number) {
    this.userService.setId(id);
    const dialogRef = this.dialog.open(UserEditComponent);
    this.updateList(dialogRef);
  }

  openDeleteDialog(id: number) {
    this.userService.setUser(id);
    const dialogRef = this.dialog.open(ModalDeleteComponent);
    this.updateList(dialogRef);
  }
  openViewDialog(id: number) {
    this.userService.setUser(id);
    this.dialog.open(UserViewModalComponent);
  }

  filterList(resultSearch) {
    this.users = resultSearch;
  }
  fetchUsers() {
    this.userService.getUsers().then(result => {
      this.users = result;
    });
  }
  updateList(dialogRef) {
    dialogRef.afterClosed().subscribe(result => {
      this.fetchUsers();
    });
  }
}
