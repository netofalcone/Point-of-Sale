import { MatDialogModule } from '@angular/material/dialog';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { Routes, RouterModule } from '@angular/router';
import {MatSelectModule} from '@angular/material/select';
import { UserEditComponent } from './user-edit/user-edit.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from '../../shared/shared.module';
import { UserViewModalComponent } from './user-view-modal/user-view-modal.component';


const routes: Routes = [
  {
    path: '',
    component: UserListComponent,
  }
];
@NgModule({
  declarations: [UserListComponent, UserEditComponent, UserViewModalComponent],
  entryComponents:[UserEditComponent],
  imports: [
    CommonModule,
    RouterModule,
    RouterModule.forChild(routes),
    MatSelectModule,
    MatDialogModule,
    NgSelectModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
  ]
})
export class UserModule { }
