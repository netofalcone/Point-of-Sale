import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/components/login/service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private loginService: LoginService) {

   }

  ngOnInit(): void {
  }
  logOut() {
    this.loginService.logOut();
  }
  headerVisible() {
    if (localStorage.getItem('token') === null ) {
     return false;
    } else {
      return true;
    }
  }

}
