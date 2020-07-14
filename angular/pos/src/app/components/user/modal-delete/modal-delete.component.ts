import { UserService } from './../user.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-modal-delete',
  templateUrl: './modal-delete.component.html',
  styleUrls: ['./modal-delete.component.css']
})
export class ModalDeleteComponent implements OnInit {

  constructor(private dialog: MatDialog, private service: UserService) { }

  ngOnInit(): void {
  }
  public goToList(): void {
    this.dialog.closeAll();
  }

  public delete(): void{
    this.service.delete(this.dialog);
  }
}
