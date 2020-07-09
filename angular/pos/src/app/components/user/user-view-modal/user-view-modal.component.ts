import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {UserService} from "../user.service";

@Component({
  selector: 'app-user-view-modal',
  templateUrl: './user-view-modal.component.html',
  styleUrls: ['./user-view-modal.component.css']
})
export class UserViewModalComponent implements OnInit {

  user: any;

  constructor(private dialog: MatDialog, private service: UserService) {
   this.service.getUserByID().subscribe(result => {
      this.user = result;
    });
  }


  ngOnInit(): void {
  }

  public goToList(): void {
    this.dialog.closeAll();
  }
}
