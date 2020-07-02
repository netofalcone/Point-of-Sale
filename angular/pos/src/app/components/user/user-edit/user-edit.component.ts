import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../user.service';
import { Validation } from '../validation';

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
      id: new FormControl('', []),
      name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255), Validation.validateFieldName()]),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', [Validators.required]),
      cpf: new FormControl('', [Validators.required, Validators.maxLength(20), Validation.validateCpf()]),
      newPassword: new FormControl('', [Validators.required, Validation.equalsTO('passwordConfirm')]),
      passwordConfirm: new FormControl('', [Validators.required, Validation.equalsTO('newPassword')]),
      role: new FormControl('', [Validators.required])

    });
  }
  onSubmit() {
    this.editForm.removeControl('passwordConfirm');
    if (this.editForm.get('id').value === '') {
      this.editForm.removeControl('id');
    }
    this.userService.createUser(this.editForm.value, this.dialog);
  }
}
