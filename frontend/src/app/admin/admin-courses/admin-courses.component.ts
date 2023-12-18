import { Component } from '@angular/core';
import {CourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";

@Component({
  selector: 'app-admin-courses',
  templateUrl: './admin-courses.component.html',
  styleUrls: ['./admin-courses.component.css']
})
export class AdminCoursesComponent {

  coursesList: CourseFilterDTO[] = [];
  courseType: {name: string, value: string} | null = null;
  courseStatus: {name: string, value: string} | null = null;
  courseCity: {name: string, value: string} | null = null;
  startDate: Date | null = null;
  endDate: Date | null = null;
  registeredParticipants: number | null = null;
  maxParticipantsNumber: number | null = null;
  sortBy: {name: string, value: string} | null = null;

  // TODO: pipes dla courseType, courseStatus, courseCity

  doNothing() {
    // do zmiany na showAddCourseModal()
  }

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService) {
  }

  findCourses(): void {
    this.restClient.getCoursesByFilters(this.courseType!.name, this.courseStatus!.name,
      this.courseCity!.name, this.startDate, this.endDate, this.registeredParticipants,
      this.maxParticipantsNumber, this.sortBy!.value)
      .subscribe( (foundedCourses) => {
        this.coursesList = foundedCourses;
      }, error => {
        this.responseHandlerService.handleErrorsPtoasts(error);
      });
  }

  // select input values

  sortByValues = [
    { name: "Rodzaj", value: "type"},
    { name: "Status", value: "status"},
    { name: "Miasto", value: "city"},
    { name: "Data rozpoczęcia", value: "dateFrom"},
    { name: "Data zakończenia", value: "dateTo"},
    { name: "Zapisanych kursantów", value: "participants"}, // nwm czy to będzie działać, możliwa poprawa na backendzie tego
    { name: "Maksymalna liczba kursantów", value: "maxParticipantsNumber"}
  ]

  courseTypes = [
    { name: "STERNIK_MOTOROWODNY", value: "Sternik motorowodny"},
    { name: "MOTOROWODNY_STERNIK_MORSKI", value: "Motorowodny sternik morski"},
    { name: "HOLOWANIE_NARCIARZA_I_OBIEKTOW_NAWODNYCH", value: "Holowanie narciarza i obiektow nawodnych"},
    { name: "ZEGLARZ_JACHTOWY", value: "Żeglarz jachtowy"},
    { name: "JACHTOWY_STERNIK_MORSKI", value: "Jachtowy sternik morski"},
  ];

  courseStatuses = [
    { name: "NIEROZPOCZETY", value: "Nierozpoczęty"},
    { name: "ROZPOCZETY", value: "Rozpoczęty"},
    { name: "ZAKONCZONY", value: "Zakończony"}
  ]

  courseCities = [
    { name: "SOPOT", value: "Sopot"},
    { name: "OLECKO", value: "Olecko"}
  ]

}
