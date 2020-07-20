import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CategoryService } from '../category.service';

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.css']
})
export class CategoryEditComponent implements OnInit {

  editForm: FormGroup;
  label: string;
  labelBtnSubmit:string;

  constructor(private fb: FormBuilder, public dialog: MatDialog, private categoryService: CategoryService) {
    this.label = 'Cadastrar Categoria';
    this.labelBtnSubmit = 'Cadastrar';
  }

  ngOnInit( ): void {
    this.createForm();
  }

  createForm() {
    this.editForm = this.fb.group({
      id: new FormControl('', []),
      name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(250)]),
      description: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),

    });
  }

  public goToList(): void {
    this.dialog.closeAll();
  }

  onSubmit() {
      this.categoryService.createUser(this.editForm.value, this.dialog);
    }
  }



