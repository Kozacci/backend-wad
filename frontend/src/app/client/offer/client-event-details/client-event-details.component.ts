import {Component, Input} from '@angular/core';
import {RestClient} from "../../../shared/rest-client";
import {AuthService} from "../../../shared/services/auth/auth.service";
import {MessageService} from "primeng/api";
import {EventFilterDTO, ParticipantEventEntityCreateDTO} from "../../../shared/dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FormService} from "../../../shared/services/form/form.service";

export interface EventDetails {
  type: string,
  title: string,
  image: string,
  profits: string[],
  conditions: string[],
  duration: string,
  yacht: string,
  additionalInfo1: string,
  additionalInfo2: string,
  additionalInfo3: string,
  additionalInfo4: string,
  price: string,
  availableEvents: EventFilterDTO[];
}

@Component({
  // TODO Animation for price block
  // animations: [
  //   trigger('fadeInOut', [
  //     transition(':enter', [   // :enter jest aliasem do 'void => *'
  //       style({ opacity: 0 }),
  //       animate(500, style({ opacity: 1 }))
  //     ]),
  //     transition(':leave', [   // :leave jest aliasem do '* => void'
  //       animate(500, style({ opacity: 0 }))
  //     ])
  //   ])
  // ],
  selector: 'app-client-event-details',
  templateUrl: './client-event-details.component.html',
  styleUrls: ['../client-course-details/client-course-event-details.component.css']
})
export class ClientEventDetailsComponent {

  modalDateVisibility: boolean = false;
  modalFormVisibility: boolean = false;
  participantEventCreateDTO: ParticipantEventEntityCreateDTO = <ParticipantEventEntityCreateDTO>{};

  @Input()
  event: EventDetails = <EventDetails>{};
  availableEvents: EventFilterDTO[] = <EventFilterDTO[]>[];
  chosenEvent: EventFilterDTO = <EventFilterDTO>{};

  participantsNumber: number = 1;
  finalCost: number = 0;

  formGroup = new FormGroup({
    ordererFirstName:
      new FormControl(
        '',
        [
          Validators.required,
          Validators.minLength(2)
        ]
      ),
    ordererLastName:
      new FormControl(
        '',
        [
          Validators.required,
          Validators.minLength(2)
        ]
      ),
    ordererEmail:
      new FormControl(
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.email
        ]
      ),
    ordererPhoneNumber:
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
    private readonly restClient: RestClient,
    public readonly authService: AuthService,
    private readonly messageService: MessageService,
    public readonly formService: FormService,
  ) {}

  ngOnInit(): void {
    this.setDefaultForm();
    this.checkAvailableEvents()
  }

  checkAvailableEvents() {
    this.restClient.getEventsByFilters(
      this.event.type,
      undefined,
      undefined,
    )
      .subscribe(
        response => {
          console.log(response);
          this.availableEvents = response;
          this.event.availableEvents = response;
          if(response == null) {
            this.availableEvents = [];
          }
        });
  }

  selectAvailableEvent(row: any) {
    this.chosenEvent.id = row.eventId;
    this.chosenEvent.type = row.type;
    this.chosenEvent.city = row.city;
    this.chosenEvent.cost = row.cost;
    this.chosenEvent.duration = row.duration;
    this.chosenEvent.assignedParticipants = row.assignedParticipants;
    this.chosenEvent.maxParticipantsNumber = row.maxParticipantsNumber;
    this.finalCost = row.cost;
  }

  signForEventModalChange(): void {
    this.modalFormVisibility = true;
    this.modalDateVisibility = false;
  }

  updateFinalCost() {
    this.finalCost = this.participantsNumber * this.chosenEvent.cost;
  }

  // TODO -- e-learning and my-profile
  // TODO -- navigation from homepage to offer(courses/events details)
  // TODO -- respons for courses/events signing modals
  // TODO -- audit/fix for event/courses cost on all sites
  signForEvent() {
    if (this.chosenEvent.id != null && this.formGroup.valid) {
      this.participantEventCreateDTO.eventId = this.chosenEvent.id;
      this.participantEventCreateDTO.ordererEmail = this.formGroup.value.ordererEmail?? '';
      this.participantEventCreateDTO.ordererFirstName = this.formGroup.value.ordererFirstName?? '';
      this.participantEventCreateDTO.ordererLastName = this.formGroup.value.ordererLastName?? '';
      this.participantEventCreateDTO.ordererPhoneNumber = this.formGroup.value.ordererPhoneNumber?? '';
      this.participantEventCreateDTO.participantsNumber = this.participantsNumber;
      this.restClient.signInOnEvent(this.participantEventCreateDTO)
        .subscribe( response => {
            this.messageService.add({
              life: 4000,
              severity: 'success',
              summary: 'Zapis na event',
              detail: "Udało Ci się pomyślnie zapisać na event"
            })
            this.checkAvailableEvents();
            this.modalFormVisibility = false;
            // Todo navigate to my events view
          },
          error => {
            this.messageService.add({
              life: 4000,
              severity: 'error',
              summary: 'Zapis na event',
              detail: error.error.message
            });
          });
    }
    else {
      this.messageService.add({
        life: 4000,
        severity: 'error',
        summary: 'Zapis na event',
        detail: "Wypełnij najpierw formularz"
      });
    }
  }

  setDefaultForm(): void {
    if(this.authService.isLogged()) {
      this.formGroup.get('ordererEmail')?.patchValue(sessionStorage.getItem('cacheEmail'));
      this.formGroup.get('ordererFirstName')?.patchValue(sessionStorage.getItem('cacheFirstName'));
      this.formGroup.get('ordererLastName')?.patchValue(sessionStorage.getItem('cacheLastName'));
      this.formGroup.get('ordererPhoneNumber')?.patchValue(sessionStorage.getItem('cachePhoneNumber'));
    }
  }

}
