import { Component } from '@angular/core';
import {CourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormControl, FormGroup} from "@angular/forms";

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
  registeredParticipants: number | null = null;
  maxParticipantsNumber: number | null = null;
  sortBy: {name: string, value: string} | null = null;
  dateFormGroup: FormGroup = new FormGroup({
    startDate: new FormControl(),
    endDate: new FormControl()
  })

  // TODO: pipes dla courseType, courseStatus, courseCity

  doNothing() {
    // do zmiany na showAddCourseModal()
  }

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService) {
  }

  findCourses(): void {
    this.restClient.getCoursesByFilters(this.courseType?.name, this.courseStatus?.name,
      this.courseCity?.name, this.getStartDate(), this.getEndDate(), this.registeredParticipants,
      this.maxParticipantsNumber, this.sortBy?.value)
      .subscribe( (foundedCourses) => {
        this.coursesList = foundedCourses;
      }, error => {
        this.responseHandlerService.handleErrorsPtoasts(error);
      });
  }

  formatDateToYYYYMMDD(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // adding 1, because january == 0
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  getStartDate(): string | null {
    const val = this.dateFormGroup.get('startDate')?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  setStartDate(date: Date): void {
    this.dateFormGroup.get('startDate')?.setValue(date);
  }

  getEndDate(): string | null {
    const val = this.dateFormGroup.get('endDate')?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  setEndDate(date: Date): void {
    this.dateFormGroup.get('endDate')?.setValue(date);
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
    { name: "JACHTOWY_STERNIK_MORSKI", value: "Jachtowy sternik morski"},
    { name: "MOTOROWODNY_STERNIK_MORSKI", value: "Motorowodny sternik morski"},
    { name: "ZEGLARZ_JACHTOWY", value: "Żeglarz jachtowy"},
    { name: "WARSZTATY_NAWIGACYJNE", value: "Warsztaty nawigacyjne"},
    { name: "REJSY_STAZOWE", value: "Rejsy stażowe"},
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
