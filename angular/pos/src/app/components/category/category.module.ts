import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Routes, RouterModule } from "@angular/router";
import { CategoryEditComponent } from "./category-edit/category-edit.component";
import { ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from '../../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

const routes: Routes = [
  {
    path: '',
    component: CategoryEditComponent
  }
];

@NgModule({
  declarations: [CategoryEditComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule
  ]

})
export class CategoryModule {}
