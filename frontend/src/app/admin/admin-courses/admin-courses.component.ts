import {Component} from '@angular/core';
import {
  CourseCity,
  CourseCreateUpdateDTO,
  CourseEntityDTO,
  CourseFilterDTO, CourseType,
  GroupedErrorDTO, mapToCourseCity, mapToCourseStatus, mapToCourseType,
  NameValueNull
} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FormService} from "../../shared/services/form/form.service";

@Component({
  selector: 'app-admin-courses',
  templateUrl: './admin-courses.component.html',
  styleUrls: ['./admin-courses.component.css']
})
export class AdminCoursesComponent {

  DATE_FROM: string = 'dateFrom';
  DATE_TO: string = 'dateTo';
  coursesList: CourseFilterDTO[] = [];
  courseType: NameValueNull | null = null;
  courseStatus: NameValueNull | null = null;
  courseCity: NameValueNull | null = null;
  courseCityToAdd: NameValueNull | null = null;
  registeredParticipants: number | null = null;
  maxParticipantsNumber: number | null = null;
  sortBy: NameValueNull = null;
  dateFormGroup: FormGroup = new FormGroup({
    dateFrom: new FormControl(),
    dateTo: new FormControl()
  })
  dateToAddFormGroup: FormGroup = new FormGroup({
    dateFrom: new FormControl(),
    dateTo: new FormControl()
  })
  courseTypeToAdd: NameValueNull = null;
  addCourseModalVisible: boolean = false;
  editCourseModalVisible: boolean = false;
  groupedErrors: GroupedErrorDTO[] = [];
  courseToAdd: CourseCreateUpdateDTO = <CourseCreateUpdateDTO><unknown>{
    courseType: null, city: null, dateTo: null, dateFrom: null, maxParticipantsNumber: null
  };
  formGroupAddCourse = new FormGroup({
    maxParticipantsNumber: new FormControl(this.courseToAdd.maxParticipantsNumber,
      [Validators.required])
  })


  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService,
              protected formService: FormService) {
  }

  findCourses(): void {
    this.restClient.getCoursesByFilters(this.courseType?.name, this.courseStatus?.name,
      this.courseCity?.name, this.getDateFromDate(), this.getDateToDate(), this.registeredParticipants,
      this.maxParticipantsNumber, this.sortBy?.value)
      .subscribe( (foundedCourses) => {
        this.coursesList = foundedCourses;
      }, error => {
        this.responseHandlerService.handleErrorsPtoasts(error);
      });
  }

  addCourse(): void {
    this.groupedErrors = [];
    this.courseToAdd.courseType = <CourseType>this.courseTypeToAdd?.value;
    this.courseToAdd.city = <CourseCity>this.courseCityToAdd?.value;
    this.courseToAdd.maxParticipantsNumber = <number>this.formGroupAddCourse.value.maxParticipantsNumber;
    this.courseToAdd.dateFrom = this.getAddDateFromDate();
    this.courseToAdd.dateTo = this.getAddDateToDate();
    this.restClient.addCourse(this.courseToAdd).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Dodanie kursu", "Kurs został dodany pomyślnie.");
      this.appendCourseToTable(response);
    }, error => {
      this.groupedErrors = this.responseHandlerService.getErrorsBelowInputs(error);
    })
  }

  private appendCourseToTable(response: CourseEntityDTO) {
    this.coursesList = this.coursesList.concat([<CourseFilterDTO>{
      id: response.id,
      courseType: mapToCourseType(response.courseType),
      dateFrom: response.dateFrom,
      dateTo: response.dateTo,
      courseStatus: mapToCourseStatus(response.courseStatus),
      maxParticipantsNumber: response.maxParticipantsNumber,
      registeredParticipants: response.assignedParticipantsNumber,
      city: mapToCourseCity(response.city)
    }])
  }

  insertDataIntoEditCourseModal(question: CourseFilterDTO) {

  }

  formatDateToYYYYMMDD(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // adding 1, because january == 0
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  // getters & setters

  getDateFromDate(): string | null {
    const val = this.dateFormGroup.get(this.DATE_FROM)?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  setDateFromDate(date: Date): void {
    this.dateFormGroup.get(this.DATE_FROM)?.setValue(date);
  }

  getDateToDate(): string | null {
    const val = this.dateFormGroup.get(this.DATE_TO)?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  setDateToDate(date: Date): void {
    this.dateFormGroup.get(this.DATE_TO)?.setValue(date);
  }

  setAddDateFromDate(date: Date): void {
    this.dateToAddFormGroup.get(this.DATE_FROM)?.setValue(date);
  }

  getAddDateFromDate(): string | null {
    const val = this.dateToAddFormGroup.get(this.DATE_FROM)?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  setAddDateToDate(date: Date): void {
    this.dateToAddFormGroup.get(this.DATE_TO)?.setValue(date);
  }

  getAddDateToDate(): string | null {
    const val = this.dateToAddFormGroup.get(this.DATE_TO)?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  // show/close modal methods

  showAddCourseModal() {
    this.addCourseModalVisible = true;
  }

  closeAddCourseModal() {
    this.addCourseModalVisible = false
  }

  showEditCourseModal() {
    this.editCourseModalVisible = true;
  }

  closeEditCourseModal() {
    this.editCourseModalVisible = false;
  }

  // select input values

  sortByValues = [
    { name: "Rodzaj", value: "type"},
    { name: "Status", value: "status"},
    { name: "Miasto", value: "city"},
    { name: "Data rozpoczęcia", value: "dateFrom"},
    { name: "Data zakończenia", value: "dateTo"},
    { name: "Zapisanych kursantów", value: "participants"},
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
