import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../shared/services/auth/auth.service";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-navbar',
  templateUrl: './client-navbar.component.html',
  styleUrls: ['./client-navbar.component.css']
})
export class ClientNavbarComponent implements OnInit {

  constructor(
    public authService: AuthService,
    public readonly pathService: PathService
  ) {}

  ngOnInit(): void {
  }
}
