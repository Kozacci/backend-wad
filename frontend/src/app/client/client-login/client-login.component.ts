import { Component } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";
import {ParticipantLoginDTO} from "../../shared/dto";

@Component({
  selector: 'app-client-login',
  templateUrl: './client-login.component.html',
  styleUrls: ['./client-login.component.css']
})
export class ClientLoginComponent {

  passwordHide: boolean = true;
  email =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.email,
        Validators.minLength(5)
      ]
    );
  password =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.minLength(8)
      ]
    );


  constructor(public readonly authService: AuthService)
  { }

  login(participantLoginDTO: ParticipantLoginDTO) { // TODO
  }

  // Todo - Create component for input labels and create error messages system for them
  getInputErrorMessage(input: FormControl<string | null>) {
    if (input.hasError('required')) {
      return 'Musisz wypełnić pole!';
    }
    else if (input.hasError('minlength')) {
      const requiredLength = input.getError('minlength').requiredLength;
      return 'Pole musi zawierać min. ' + requiredLength + ' znaków!';
    }
    else {
      return 'Niepoprawne pole';
    }
  }

  getEmailErrorMessage(input: FormControl<string | null>) {
    if (input.hasError('required')) {
      return 'Musisz wypełnić email!';
    }
    return this.email.hasError('email') ? 'Niepoprawny email' : '';
  }

}
