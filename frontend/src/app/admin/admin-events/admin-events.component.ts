import {Component} from '@angular/core';
import {
  EventCity,
  EventCreateUpdateDTO,
  EventEntityDTO,
  EventFilterDTO, EventType,
  GroupedErrorDTO,
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
      [Validators.required])
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
      this.eventsList = foundedEvents;
    })
  }

  addEvent(): void {
    this.groupedErrors = [];
    this.eventToAdd.type = <EventType>this.eventTypeToAdd?.value;
    this.eventToAdd.city = <EventCity>this.eventCityToAdd?.value;
    this.eventToAdd.date = <Date>this.addEventFormGroup.value.date;
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
      id: addedEvent.id,
      city: <EventCity>addedEvent.city, // możliwe ze bedzie trzeba dodać mapowanie, sprawdzam czy działa
      type: <EventType>addedEvent.type,
      cost: addedEvent.cost,
      date: addedEvent.date,
      duration: addedEvent.duration,
      maxParticipantsNumber: addedEvent.maxParticipantsNumber,
      assignedParticipants: addedEvent.assignedParticipants,
    }])
  }

  insertDataIntoEditEventModal(eventFilterDTO: EventFilterDTO): void {

  }

  formatDateToLocalTimeString(date: Date) {
    if (date == null) return null;
    date.setSeconds(0);
    return this.datePipe.transform(date, 'HH:mm:ss')
  }

  showAddEventModal(): void {
    this.addEventModalVisible = true;
  }

  closeAddEventModal(): void {
    this.addEventModalVisible = false;
    this.groupedErrors = [];
    this.addEventFormGroup.clearAsyncValidators();
  }

  // getters & setters

  setDateAtEventAddModal(date: Date) {

  }

  setDurationAtEventAddModal(date: Date) {

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
