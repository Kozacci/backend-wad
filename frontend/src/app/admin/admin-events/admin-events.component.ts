import {Component} from '@angular/core';
import {
  EventCity,
  EventCreateUpdateDTO,
  EventEntityDTO,
  EventFilterDTO, EventType,
  GroupedErrorDTO, mapToEventCity, mapToEventType,
  NameValueNull
} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FormService} from "../../shared/services/form/form.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-admin-events',
  templateUrl: './admin-events.component.html',
  styleUrls: ['./admin-events.component.css']
})
export class AdminEventsComponent {

  onlyDigitsAndOneDot: RegExp = /^\d*\.?\d*$/;
  eventType: NameValueNull = null;
  eventCity: NameValueNull = null;
  sortBy: NameValueNull = null;
  maxParticipantsNumber: number | undefined = undefined;
  cost: number | undefined = undefined;
  adminSearch: boolean = true;
  eventsList: EventFilterDTO[] = [];
  groupedErrors: GroupedErrorDTO[] = [];
  addEventModalVisible: boolean = false;
  eventTypeToAdd: NameValueNull = null;
  eventCityToAdd: NameValueNull = null;
  eventToAdd: EventCreateUpdateDTO = <EventCreateUpdateDTO><unknown>{
    cost: null, city: null, maxParticipantsNumber: null, date: null, duration: null, type: null
  };
  private today = new Date();
  addEventFormGroup= new FormGroup({
    date: new FormControl(new Date(this.today.getFullYear(), this.today.getMonth(),
        this.today.getDate(), 12, 0, 0, 0),
      [Validators.required]),
    duration: new FormControl(new Date(2023, 0, 1,
        1, 0, 0 ,0),
      [Validators.required]),
    maxParticipantsNumber: new FormControl(this.eventToAdd.maxParticipantsNumber,
      [Validators.required]),
    cost: new FormControl(this.eventToAdd.cost,
      [Validators.required,
                    Validators.pattern(this.onlyDigitsAndOneDot)])
  })
  editEventModalVisible: boolean = false
  eventToEdit: EventCreateUpdateDTO = <EventCreateUpdateDTO><unknown>{
    cost: null, city: null, maxParticipantsNumber: null, date: null, duration: null, type: null
  };
  eventToEditId: number | null = null;
  eventTypeToEdit: NameValueNull = null;
  eventCityToEdit: NameValueNull = null;
  editEventFormGroup= new FormGroup({
    date: new FormControl(new Date(this.today.getFullYear(), this.today.getMonth(),
        this.today.getDate(), 12, 0, 0, 0),
      [Validators.required]),
    duration: new FormControl(new Date(2023, 0, 1,
        1, 0, 0 ,0),
      [Validators.required]),
    maxParticipantsNumber: new FormControl(this.eventToEdit.maxParticipantsNumber,
      [Validators.required]),
    cost: new FormControl(this.eventToEdit.cost,
      [Validators.required, Validators.pattern(this.onlyDigitsAndOneDot)]) // validator na
  })

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService,
              protected formService: FormService,
              private datePipe: DatePipe) {
  }

  findEvents(): void {
    this.restClient.getEventsByFilters(this.eventType?.name,
                    this.eventCity?.name,
                    this.cost,
                    this.maxParticipantsNumber,
                    this.sortBy?.value,
                    this.adminSearch
      ).subscribe(foundedEvents => {
      console.log(foundedEvents);
      this.eventsList = foundedEvents;
    })
  }

  addEvent(): void {
    this.groupedErrors = [];
    this.eventToAdd.type = <EventType>this.eventTypeToAdd?.value;
    this.eventToAdd.city = <EventCity>this.eventCityToAdd?.value;
    let dateToAdd = <Date>this.addEventFormGroup.value.date;
    dateToAdd.setHours(dateToAdd.getHours() + 1)
    this.eventToAdd.date = dateToAdd;
    this.eventToAdd.duration = this.formatDateToLocalTimeString(<Date>this.addEventFormGroup.value.duration);
    this.eventToAdd.cost = <number>this.addEventFormGroup.value.cost;
    this.eventToAdd.maxParticipantsNumber = <number>this.addEventFormGroup.value.maxParticipantsNumber;
    this.restClient.addEvent(this.eventToAdd).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Dodanie eventu", "Event został dodany pomyślnie.");
      this.appendEventToTable(response);
    }, error => {
      this.groupedErrors = this.responseHandlerService.getErrorsBelowInputs(error);
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  appendEventToTable(addedEvent: EventEntityDTO): void {
    this.eventsList = this.eventsList.concat([<EventFilterDTO>{
      eventId: addedEvent.id,
      city: mapToEventCity(addedEvent.city),
      type: mapToEventType(addedEvent.type),
      cost: addedEvent.cost,
      date: addedEvent.date,
      duration: addedEvent.duration,
      maxParticipantsNumber: addedEvent.maxParticipantsNumber,
      assignedParticipants: addedEvent.assignedParticipants, // otherwise it shows nothing
    }])
  }

  editEvent(): void {
    this.eventToEdit.cost = <number>this.editEventFormGroup.value.cost;
    this.eventToEdit.duration = this.formatDateToLocalTimeString(<Date>this.editEventFormGroup.value.duration);
    let dateToEdit = <Date>this.editEventFormGroup.value.date;
    dateToEdit.setHours(dateToEdit.getHours() + 1)
    this.eventToEdit.date = dateToEdit;
    this.eventToEdit.city = <EventCity>this.eventCityToEdit?.value;
    this.eventToEdit.type = <EventType>this.eventTypeToEdit?.value;
    this.eventToEdit.maxParticipantsNumber = <number>this.editEventFormGroup.value.maxParticipantsNumber;
    this.restClient.editEvent(this.eventToEdit, this.eventToEditId!).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Edycja eventu", "Event numer: " + response.id + " został zedytowany.");
      this.changeEditedEventValuesInTable();
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  deleteEvent(): void {
    this.restClient.deleteEventById(this.eventToEditId).subscribe(() => {
      this.responseHandlerService.showSuccessPToast("Usunięcie eventu", "Event numer: " + this.eventToEditId + " został usunięty.");
      this.removeEventFromTable(this.eventToEditId);
      this.closeEditEventModal();
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  insertDataIntoEditEventModal(event: EventFilterDTO) {
    this.eventToEditId = event.eventId;
    this.eventTypeToEdit = this.eventTypes.find(type => type.name == event.type.toString())!;
    this.eventCityToEdit = this.eventCities.find(city => city.name == event.city.toString())!;
    this.editEventFormGroup.controls.date.setValue(new Date(event.date));
    this.editEventFormGroup.controls.duration.setValue(this.createValidTimeDate(event.duration));
    this.editEventFormGroup.controls.cost.setValue(event.cost);
    this.editEventFormGroup.controls.maxParticipantsNumber.setValue(event.maxParticipantsNumber);
    this.showEditEventModal();
  }

  // receives Date type as 'HH:mm:ss' and returns correct Date type, so it can be displayed at view
  createValidTimeDate(givenDate: Date): Date {
    let timeStr = givenDate.toString();
    const parts = timeStr.split(":");

    if (parts.length !== 3) {
      console.error("Invalid date format:", givenDate, "  at createValidTimeDate");
    }

    const hours = parseInt(parts[0], 10);
    const minutes = parseInt(parts[1], 10);
    const seconds = parseInt(parts[2], 10);

    const date = new Date();
    date.setHours(hours, minutes, seconds, 0);

    return date;
  }

  formatDateToLocalTimeString(date: Date) {
    if (date == null) return null;
    date.setSeconds(0);
    return this.datePipe.transform(date, 'HH:mm:ss')
  }

  showEditEventModal(): void {
    this.editEventModalVisible = true;
  }

  showAddEventModal(): void {
    this.addEventModalVisible = true;
  }

  closeAddEventModal(): void {
    this.addEventModalVisible = false;
    this.groupedErrors = [];
    this.addEventFormGroup.clearAsyncValidators();
  }

  closeEditEventModal(): void {
    this.editEventModalVisible = false;
    this.groupedErrors = [];
    this.editEventFormGroup.clearAsyncValidators();
  }

  changeEditedEventValuesInTable() {
    let editedEvent = this.eventsList.find(event => event.eventId == this.eventToEditId)!;
    editedEvent.type = mapToEventType(this.eventToEdit.type);
    editedEvent.city = mapToEventCity(this.eventToEdit.city);
    editedEvent.cost = this.eventToEdit.cost;
    editedEvent.date = this.eventToEdit.date;
    editedEvent.duration = <Date>this.eventToEdit.duration;
    editedEvent.maxParticipantsNumber = this.eventToEdit.maxParticipantsNumber;
  }

  removeEventFromTable(deletedEventId: number | null) {
    if (deletedEventId == null) return;

    this.eventsList = this.eventsList.filter(events => events.eventId !== deletedEventId);
  }

  // select input values

  eventTypes= [
    { name: "REJS_WIDOKOWY", value: "Rejs widokowy"},
    { name: "PANIENSKI", value: "Wieczór panieński"},
    { name: "KAWALERSKI", value: "Wieczór kawalerski"},
    { name: "WYNAJEM_SKUTERA", value: "Wynajem skutera wodnego"},
    { name: "EVENT_DLA_FIRMY", value: "Event dla firmy"}
  ];

  eventCities = [
    { name: "SOPOT", value: "Sopot"},
    { name: "OLECKO", value: "Olecko"},
    { name: "GDANSK", value: "Gdańsk"}
  ]

  sortByValues = [
    { name: "Rodzaj", value: "type"},
    { name: "Miasto", value: "city"},
    { name: "Koszt", value: "cost"},
    { name: "Maksymalna liczba kursantów", value: "maxParticipantsNumber"}
  ]
}
