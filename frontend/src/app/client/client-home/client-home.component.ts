import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../shared/services/auth/auth.service";

@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.css']
})
export class ClientHomeComponent implements OnInit {

  constructor(private readonly authService: AuthService)
  {}

  ngOnInit() {
    this.authService.isLogged();
    this.authService.messageIfNotLogged();
  }

}
