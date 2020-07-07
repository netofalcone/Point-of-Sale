import { Validation } from './../validation';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../user.service';
import { async } from '@angular/core/testing';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  roles = [{ id: 1, name: 'Super User' }];
  editForm: FormGroup;
  label: string;
  labelBtnSubmit:string;

  constructor(public dialog: MatDialog, private fb: FormBuilder, private userService: UserService) {
    this.isEditMode();
  }
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
      email: new FormControl('', [Validators.required, Validators.email, Validation.validateFieldEmail()]),
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
    if (this.userService.isEditMode()) {
      this.userService.updateUser(this.editForm.value, this.dialog);
    } else {
      this.userService.createUser(this.editForm.value, this.dialog);
    }
  }
  isEditMode() {
    if (this.userService.isEditMode()) {
      this.userService.getUser().subscribe(result => {
        this.setFormControlEditMode(result);
      });
      this.label = 'Editar Usuário';
      this.labelBtnSubmit = 'Salvar';
      return true;
    }
    this.label = 'Cadastrar Usuário';
    this.labelBtnSubmit = 'Cadastrar';
    return false;
  }

  private setFormControlEditMode(result: Object) {
    this.editForm.get('id').setValue(result['id']);
    this.editForm.get('name').setValue(result['name']);
    this.editForm.get('email').setValue(result['email']);
    this.editForm.get('phone').setValue(result['phone']);
    this.editForm.get('role').setValue(result['role']);
    this.editForm.get('cpf').setValue(result['cpf']);
    this.editForm.get('newPassword').disable();
    this.editForm.get('passwordConfirm').disable();
    this.editForm.get('cpf').disable();
  }
}
