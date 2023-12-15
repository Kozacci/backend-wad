import { Component } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";
import {ParticipantRegisterDTO} from "../../shared/dto";

@Component({
  selector: 'app-client-register',
  templateUrl: './client-register.component.html',
  styleUrls: ['./client-register.component.css']
})
export class ClientRegisterComponent {

  passwordHide: boolean = true;
  name =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.minLength(4)
      ]
    );
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
  passwordRepeat =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.minLength(8)
      ]
    );
  phoneNumber =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.maxLength(9)
      ]
    );


  constructor(public readonly authService: AuthService)
  { }

  register(participantRegisterDTO: ParticipantRegisterDTO) { // TODO
  }

  // Todo - Create component for input labels and create error messages system for them
  getInputErrorMessage(input: FormControl<string | null>) {
    if (input.hasError('required')) {
      return 'Musisz wypełnić pole!';
    }
    else if (input.hasError('minlength')) {
      const maxRequiredLength = input.getError('minlength').requiredLength;
      return 'Pole musi zawierać min. ' + maxRequiredLength + ' znaków!';
    }
    else if (input.hasError('maxlength')) {
      const minRequiredLength = input.getError('maxlength').requiredLength;
      return 'Pole musi zawierać max. ' + minRequiredLength + ' znaków!';
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

  getPasswordRepeatErrorMessage(input: FormControl<string | null>) {
    if(this.password != input) {
      return 'Hasła muszą być takie same!';
    }
    return this.getInputErrorMessage(input);
  }

}
