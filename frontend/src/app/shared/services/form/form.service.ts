import {Injectable} from '@angular/core';
import {FormControl} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class FormService {

  /**
   * Method to interact with form
   * It spies specific input and checks if value has changed
   * If value has changed it checks if any error has occurred
   * And if yes, it throws it whenever we use selector <mat-error> in tag <form>
   * @param input
   * @returns error message as String
   */
  getInputErrorMessage(input: FormControl) {
    if (input.hasError('required')) {
      return 'Musisz wypełnić pole!';
    }
    else if (input.hasError('minlength')) {
      const minRequiredLength = input.getError('minlength').requiredLength;
      return 'Pole musi zawierać min. ' + minRequiredLength + ' znaków!';
    }
    else if (input.hasError('maxlength')) {
      const maxRequiredLength = input.getError('maxlength').requiredLength;
      return 'Pole musi zawierać max. ' + maxRequiredLength + ' znaków!';
    }
    else if (input.hasError('pattern')) {
      return 'Niepoprawny format';
    }
    else if (input.hasError('email')) {
      return 'Niepoprawny email!';
    }
    else {
      return 'Niepoprawne pole!';
    }
  }

}
