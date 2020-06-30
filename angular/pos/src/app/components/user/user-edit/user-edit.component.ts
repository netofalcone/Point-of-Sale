import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  roles = [{id: 1, name: 'Super User'}];
  editForm: FormGroup;
  constructor(public dialog: MatDialog, private fb: FormBuilder, private userService: UserService) { }

  ngOnInit(): void {
    this.createForm();
  }
  public goToList(): void {
    this.dialog.closeAll();
  }

  createForm() {
    this.editForm = this.fb.group({
      id: new FormControl('', [Validators.required, Validators.email]),
      name: new FormControl('', [Validators.required, Validators.email]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      cpf: new FormControl('', [Validators.required]),
      role: this.fb.group({
        id: new FormControl('', [Validators.required, Validators.email])
      })
    });
  }
  onSubmit(){
    /* TODO */
    /* Devem criar telefones ao cadastrar o usuário. */
    /* TODO */
    this.userService.createUser(this.editForm.value, this.dialog);
  }
}
