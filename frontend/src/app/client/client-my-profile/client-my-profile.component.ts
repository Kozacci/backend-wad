import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FormService} from "../../shared/services/form/form.service";
import {RestClient} from "../../shared/rest-client";
import {MessageService} from "primeng/api";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-my-profile',
  templateUrl: './client-my-profile.component.html',
  styleUrls: ['./client-my-profile.component.css']
})
export class ClientMyProfileComponent {

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
    phoneNumber:
      new FormControl(
        '',
        [
          Validators.required,
          Validators.maxLength(9),
          Validators.pattern(/^\d{9}$/)
        ]
      )
  });

  constructor(
    public readonly formService: FormService,
    public restClient: RestClient,
    private readonly messageService: MessageService,
    private readonly pathService: PathService
  ) {}

  submitForm() {
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
            this.messageService.add({life:4000, severity:'error', summary:'Rejestracja', detail: 'Pole ' + error.error[0].fieldName + ' ' + error.error[0].message})
            console.error('Błąd rejestracji', error);
          })
    }
  }

}
