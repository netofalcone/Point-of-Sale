import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { Routes, RouterModule } from '@angular/router';
import {MatSelectModule} from '@angular/material/select';
import { UserEditComponent } from './user-edit/user-edit.component';
const routes: Routes = [
  {
    path: '',
    component: UserListComponent,
  },
  {
    path: 'create',
    component: UserEditComponent,
  }
];
@NgModule({
  declarations: [UserListComponent, UserEditComponent],
  imports: [
    CommonModule,
    RouterModule,
    RouterModule.forChild(routes),
    MatSelectModule
  ]
})
export class UserModule { }
