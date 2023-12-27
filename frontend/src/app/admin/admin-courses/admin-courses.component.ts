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
  courseToEdit: CourseCreateUpdateDTO = <CourseCreateUpdateDTO><unknown>{
    courseType: null, city: null, dateTo: null, dateFrom: null, maxParticipantsNumber: null
  };
  courseToEditId: number | null = null;
  formGroupAddCourse = new FormGroup({
    maxParticipantsNumber: new FormControl(this.courseToAdd.maxParticipantsNumber,
      [Validators.required])
  })
  courseTypeToEdit: NameValueNull | undefined = undefined;
  courseCityToEdit: NameValueNull | undefined = undefined;
  formGroupEditCourse = new FormGroup({
    maxParticipantsNumber: new FormControl(this.courseToEdit.maxParticipantsNumber,
      [Validators.required]),
    dateFrom: new FormControl(new Date(),
      [Validators.required]),
    dateTo: new FormControl(new Date(),
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

  editCourse(): void {
    this.courseToEdit.courseType = <CourseType>this.courseTypeToEdit?.value;
    this.courseToEdit.city = <CourseCity>this.courseCityToEdit?.value;
    this.courseToEdit.dateFrom = <string><unknown>this.formGroupEditCourse.value.dateFrom;
    this.courseToEdit.dateTo = <string><unknown>this.formGroupEditCourse.value.dateTo;
    this.courseToEdit.maxParticipantsNumber = <number>this.formGroupEditCourse.value.maxParticipantsNumber;
    this.restClient.editCourse(this.courseToEdit, this.courseToEditId!).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Edycja kursu", "Kurs numer: " + response.id + " został zedytowany.");
      this.changeEditedCourseValuesInTable();
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error)
    })
  }

  deleteCourse(): void {
    this.restClient.deleteCourseById(this.courseToEditId).subscribe(() => {
      this.responseHandlerService.showSuccessPToast("Usunięcie kursu", "Kurs nr: " + this.courseToEditId + " został usunięty.");
      this.removeCourseFromTable(this.courseToEditId);
      this.closeEditCourseModal();
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  insertDataIntoEditCourseModal(course: CourseFilterDTO) {
    this.courseToEditId = course.id;
    this.courseTypeToEdit = this.courseTypes.find(type => type.name == course.courseType.toString());
    this.courseCityToEdit = this.courseCities.find(city => city.name == course.city.toString());
    this.formGroupEditCourse.controls.maxParticipantsNumber.setValue(course.maxParticipantsNumber);
    this.setDateFromEditModal(course.dateFrom)
    this.setDateToEditModal(course.dateTo);
    this.showEditCourseModal();
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

  removeCourseFromTable(deletedCourseId: number | null): void {
    if (deletedCourseId == null) {
      return;
    }
    this.coursesList = this.coursesList.filter(courses => courses.id !== deletedCourseId);
  }


  private changeEditedCourseValuesInTable() {
    let editedCourse = this.coursesList.find(course => course.id == this.courseToEditId)!;
    editedCourse.courseType = mapToCourseType(this.courseToEdit.courseType);
    editedCourse.city = mapToCourseCity(this.courseToEdit.city);
    editedCourse.dateFrom = new Date(this.courseToEdit.dateFrom!);
    editedCourse.dateTo = new Date(this.courseToEdit.dateTo!);
    editedCourse.maxParticipantsNumber = this.courseToEdit.maxParticipantsNumber;
  }

  formatDateToYYYYMMDD(dateInput: Date): string {
    let date: Date;

    // check if dateInput is already Date type object
    if (dateInput instanceof Date) {
      date = dateInput;
    } else {
      // conversion attempt to Date type
      date = new Date(dateInput);
    }
    // check if conversion was correct
    if (isNaN(date.getTime())) {
      console.error('Invalid date in formatDateToYYYYMMDD');
    }

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

  setDateFromEditModal(date: Date): void {
    let correctDate = new Date(date);
    this.formGroupEditCourse.get(this.DATE_FROM)?.setValue(correctDate);
  }

  getDateFromEditModal(): string | null {
    const val = this.formGroupEditCourse.get(this.DATE_FROM)?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  setDateToEditModal(date: Date): void {
    let correctDate = new Date(date);
    this.formGroupEditCourse.get(this.DATE_TO)?.setValue(correctDate);
  }

  getDateToEditModal(): string | null {
    const val = this.formGroupEditCourse.get(this.DATE_FROM)?.value;
    return val ? this.formatDateToYYYYMMDD(new Date(val)) : null;
  }

  // show/close modal methods

  showAddCourseModal() {
    this.addCourseModalVisible = true;
  }

  closeAddCourseModal() {
    this.addCourseModalVisible = false
    this.groupedErrors = [];
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
