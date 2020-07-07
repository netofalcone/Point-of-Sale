import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { AppConstants } from 'src/app/app-constants';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }
  goToUsersList() {
    this.router.navigate(['users']);
  }

}
