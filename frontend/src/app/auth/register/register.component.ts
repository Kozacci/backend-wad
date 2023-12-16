import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {FormService} from "../../shared/services/form/form.service";
import {RestClient} from "../../shared/rest-client";
import {MessageService} from "primeng/api";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  passwordHide: boolean = true;

  passwordMatchValidator(passwordKey: string, confirmPasswordKey: string): ValidatorFn {
    return (input: AbstractControl): { [key: string]: any } | null => {
      const password = input.get(passwordKey);
      const confirmPassword = input.get(confirmPasswordKey);
      if (password && confirmPassword && password.value !== confirmPassword.value) {
        input.get(confirmPasswordKey)?.setErrors({ 'passwordMismatch': true });
        return { 'passwordMismatch': true };
      }
      else {
        // Deleting 'passwordMismatch' flag
        input.get(confirmPasswordKey)?.setErrors(null);
        return null;
      }
    };
  }

  formGroup = new FormGroup({
      firstName:
        new FormControl(
          '',
          [
            Validators.required,
            Validators.minLength(2)
          ]
        ),
      lastName:
        new FormControl(
          '',
          [
            Validators.required,
            Validators.minLength(2)
          ]
        ),
      email:
        new FormControl(
          '',
          [
            Validators.required,
            Validators.minLength(5),
            Validators.email
          ]
        ),
      password:
        new FormControl(
          '',
          [
            Validators.required,
            Validators.minLength(8)
          ]
        ),
      passwordRepeat:
        new FormControl(
          '',
          [
            Validators.required
          ]
        ),
      phoneNumber:
        new FormControl(
          '',
          [
            Validators.required,
            Validators.maxLength(9),
            Validators.pattern(/^\d{9}$/)
          ]
        )
  }, {validators: this.passwordMatchValidator('password', 'passwordRepeat')
  });

  constructor(
    public readonly formService: FormService,
    public restClient: RestClient,
    private readonly messageService: MessageService,
    private readonly pathService: PathService
  ) {}

  register() {
    if(this.formGroup.valid) {
      const participantToRegister = {
        firstName: this.formGroup.value.firstName ?? '',
        lastName: this.formGroup.value.lastName?? '',
        email: this.formGroup.value.email?? '',
        password: this.formGroup.value.password?? '',
        phoneNumber: this.formGroup.value.phoneNumber ?? ''
      }
      this.restClient.register(participantToRegister)
        .subscribe(
          (response) => {
            this.pathService.navigate('autoryzacja/logowanie')
            this.messageService.add({life:5000, severity:'success', summary:'Rejestracja', detail:"Teraz możesz się zalogować"})
          },
          (error) => {
            this.messageService.add({life:4000, severity:'error', summary:'Rejestracja', detail:error})
            console.error('Błąd rejestracji', error);
          })
    }
  }

  ngOnInit() {
  }

}
