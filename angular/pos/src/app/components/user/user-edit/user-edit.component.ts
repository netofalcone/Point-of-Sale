import { Validation } from './../validation';
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
      id: new FormControl('', []),
      name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(250), Validation.validateFieldName()]),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', [Validation.validateIsPhone()]),
      cpf: new FormControl('', [Validators.required, Validators.maxLength(20), Validation.validateCpf()]),
      newPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validation.validateFieldPassword()]),
      passwordConfirm: new FormControl('', [Validators.required, Validation.equalsTO('newPassword')]),
      role: new FormControl('', [Validators.required])
    });
  }
  revalidPasswordConfirm() {
    this.editForm.get('passwordConfirm').updateValueAndValidity();
  }
  updateValidateFieldPhone() {
      if (this.editForm.get('phone') !== undefined) {
        this.editForm.get('phone').setValidators([Validators.minLength(8), Validators.maxLength(11)]);
      } else {
        this.editForm.get('phone').clearValidators();
      }
  }
  onSubmit() {
    this.userService.createUser(this.editForm.value, this.dialog);
  }
}
