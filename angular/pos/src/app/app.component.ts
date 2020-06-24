import { LoginService } from './components/login/service/login.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pos';
  constructor(private login: LoginService) {
    this.login.validLogin();
  }
}
