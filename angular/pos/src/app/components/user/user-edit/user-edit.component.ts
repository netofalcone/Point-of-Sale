import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  roles = [{id: 1, name: 'Super User'}];
  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  public goToList(): void {
    this.dialog.closeAll();
  }
}
