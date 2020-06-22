import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { AppConstants } from 'src/app/app-constants';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor() {
   }

  ngOnInit(): void {
  }

}
