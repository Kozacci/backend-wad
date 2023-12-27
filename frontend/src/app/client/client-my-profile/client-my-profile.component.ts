import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FormService} from "../../shared/services/form/form.service";
import {RestClient} from "../../shared/rest-client";
import {MessageService} from "primeng/api";
import {PathService} from "../../shared/services/path.service";
import {ParticipantUpdateDTO} from "../../shared/dto";
import {AuthService} from "../../shared/services/auth/auth.service";

@Component({
  selector: 'app-client-my-profile',
  templateUrl: './client-my-profile.component.html',
  styleUrls: ['./client-my-profile.component.css']
})
export class ClientMyProfileComponent {

  protected readonly sessionStorage = sessionStorage;

  formGroup = new FormGroup({
    firstName:
      new FormControl(
        null,
        [
          Validators.minLength(2)
        ]
      ),
    lastName:
      new FormControl(
        null,
        [
          Validators.minLength(2)
        ]
      ),
    email:
      new FormControl(
        null,
        [
          Validators.minLength(5),
          Validators.email
        ]
      ),
    phoneNumber:
      new FormControl(
        null,
        [
          Validators.maxLength(9),
          Validators.pattern(/^\d{9}$/)
        ]
      )
  });

  constructor(
    public readonly formService: FormService,
    public restClient: RestClient,
    private readonly messageService: MessageService,
  ) {}

  calculateDaysFromNow() {
    const date = sessionStorage.getItem('cacheCreatedAt')
    if(date != null) {
      const dateInMilisecondsFromString: number = Date.parse(date);
      const currentDateInMiliseconds: number = Date.now();
      const difference =  Math.floor((currentDateInMiliseconds - dateInMilisecondsFromString) / (1000 * 60 * 60 * 24)) + 1;
      if (difference < 10) {
        return difference.toString() + ' dzień';
      }
      else {
        return difference.toString() + ' dni';
      }
    }
    return '';
  }

  calculateCoursesNumber() {
    if(sessionStorage.getItem('cacheParticipantCourses') != null) {
      return sessionStorage.getItem('cacheParticipantCourses')
    }
    else {
      return '0';
    }
  }

  calculatePassedCoursesNumber() {
    if(sessionStorage.getItem('cacheParticipantPassedCourses') != null) {
      return sessionStorage.getItem('cacheParticipantPassedCourses')
    }
    else {
      return '0';
    }
  }

  updateParticipant() {
    if(this.formGroup.valid) {
      const participantUpdateDTO: ParticipantUpdateDTO = {
        firstName: this.formGroup.value.firstName,
        lastName: this.formGroup.value.lastName,
        email: null, // TODO update of email and password
        phoneNumber: this.formGroup.value.phoneNumber
      }
      this.restClient.updateParticipant(Number(sessionStorage.getItem('cacheId')), participantUpdateDTO)
        .subscribe(
          (response) => {
            this.messageService.add({life:5000, severity:'success', summary:'Edycja', detail:"Udało Ci się poprawnie zmienić dane osobowe"})
            sessionStorage.setItem('cacheFirstName', response.firstName);
            sessionStorage.setItem('cacheLastName', response.lastName);
            sessionStorage.setItem('cachePhoneNumber', response.phoneNumber);
            // if(participantUpdateDTO.email != null) {
            //   this.authService.logout();
            // }
          },
          (error) => {
            this.messageService.add({life:4000, severity:'error', summary:'Edycja', detail: 'Pole ' + error.error[0].fieldName + ' ' + error.error[0].message})
            console.error('Błąd edycji', error);
          })
    }
  }
}
