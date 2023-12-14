import { Component } from '@angular/core';
import {ParticipantLoginDTO} from "../../shared/dto";
import {AuthService} from "../../shared/services/auth/auth.service";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {

  constructor(private authService: AuthService) {
  }

  logowanieDoWywalenia(): void {
    let parti : ParticipantLoginDTO = <ParticipantLoginDTO> {
      email: 'admin@email.com',
      password: 'admin123'
    }
    this.authService.login(parti).subscribe(() => console.log("logowanie"));
  }
}
