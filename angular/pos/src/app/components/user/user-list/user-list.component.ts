import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: any;

  constructor(private userService: UserService, private router: Router) {
    this.userService.getUsers().subscribe(result => {
      this.users = result;
    });
  }

  ngOnInit(): void { }

  public goToHome(): void {
    this.router.navigate(['']);
  }

  public newUser(): void {
    this.router.navigate(['users/create']);
  }
}
