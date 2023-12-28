import { Component } from '@angular/core';
import {EventFilterDTO, NameValueNull} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";

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

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService) {
  }

  findEvents(): void {
    this.restClient.getEventsByFilters(this.eventType?.name,
                    this.eventCity?.name,
                    this.cost,
                    this.maxParticipantsNumber,
                    this.sortBy?.value,
                    this.adminSearch
      ).subscribe(response => {
      this.eventsList = response;
    })
  }

  showAddEventModal(): void {

  }

  insertDataIntoEditEventModal(eventFilterDTO: EventFilterDTO): void {

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
