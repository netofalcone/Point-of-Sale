import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: UserListComponent,
  }
];
@NgModule({
  declarations: [UserListComponent],
  imports: [
    CommonModule,
    UserModule,
    RouterModule.forChild(routes),
  ]
})
export class UserModule { }
