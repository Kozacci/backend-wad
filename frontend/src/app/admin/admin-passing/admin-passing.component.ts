import {Component} from '@angular/core';
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormService} from "../../shared/services/form/form.service";
import {
  NameValueNull,
  ParticipantCourseEntityDTO,
  ParticipantCourseFilterDTO,
  ParticipantCourseUpdateDTO
} from "../../shared/dto";
import {FormControl, FormGroup} from "@angular/forms";
import {formatDateToYYYYMMDD} from "../date-utils.service";

@Component({
  selector: 'app-admin-passing',
  templateUrl: './admin-passing.component.html',
  styleUrls: ['./admin-passing.component.css']
})
export class AdminPassingComponent {

  courseType: NameValueNull = null;
  courseCity: NameValueNull = null;
  courseStatus: NameValueNull = null;
  sortBy: NameValueNull = null;
  passingType: NameValueNull = null;
  participantLastName: string | null = null;
  participantEmail: string | null = null;
  dateFormGroup: FormGroup = new FormGroup({
    dateFrom: new FormControl()
  })
  passingsList: ParticipantCourseFilterDTO[] = [];
  selectedPassings: ParticipantCourseFilterDTO[] = [];
  showEditPassingsModal: boolean = false;

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService,
              protected formService: FormService) {
  }

  findPassings() {
    this.restClient.getCoursesByParticipantIdAndFilters(
      undefined,
      this.courseType?.name,
      this.courseStatus?.name,
      null,
      this.mapPassingType(this.passingType),
      this.sortBy?.value,
      this.participantEmail,
      this.participantLastName,
      formatDateToYYYYMMDD(this.dateFormGroup.value.dateFrom),
      this.courseCity?.name,
      null)
      .subscribe(response => {
        this.passingsList = response;
      }, error => {
        this.responseHandlerService.handleErrorsPtoasts(error);
      })
  }

  passCourses() {
    let participantCourseUpdateDTO = <ParticipantCourseUpdateDTO>{
      participantCourseIds: this.selectedPassings.map(pcf => pcf.participantCourseId),
      isPassed: true
    }
    this.restClient.updateCoursePassing(participantCourseUpdateDTO).subscribe(response => {
      this.updatePassings(response, true);
      this.responseHandlerService.showSuccessPToast("Zaliczenia", "Zaktualizowano zaliczenia.");
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  unPassCourses() {
    let participantCourseUpdateDTO = <ParticipantCourseUpdateDTO>{
      participantCourseIds: this.selectedPassings.map(pcf => pcf.participantCourseId),
      isPassed: false
    }
    this.restClient.updateCoursePassing(participantCourseUpdateDTO).subscribe(response => {
      this.updatePassings(response, false);
      this.responseHandlerService.showSuccessPToast("Zaliczenia", "Zaktualizowano zaliczenia.");
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  openEditPassingsModal() {
    if (this.selectedPassings.length == 0) {
      this.responseHandlerService.showWarningPToast("Zaliczenia", "Najpierw wybierz kursantów, którym chcesz zaliczyć kurs.");
      return;
    }
    // TODO: dodać możliwość zmiany na oplacony/nieoplacony
    this.showEditPassingsModal = true;
  }

  closeEditPassingsModal() {
    this.showEditPassingsModal = false;
  }

  updatePassings(response: ParticipantCourseEntityDTO[], isPassed: boolean) {
    let changedRecordsIds = response.map(dto => dto.participantCourseId);
    this.passingsList.forEach(record => {
      if (changedRecordsIds.includes(record.participantCourseId)) {
        record.isPassed = isPassed;
      }
    })
  }

  mapPassingType(passingType: NameValueNull): boolean | null {
    if (passingType == null) return null;
    return passingType.name === "true";
  }

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

  passingTypes = [
    {name: "true", value: "Zaliczony"},
    {name: "false", value: "Niezaliczony"}
  ]

  sortByValues = [
    {name: "Miasto", value: "city"},
    {name: "Data rozpoczęcia kursu", value: "dateFrom"},
    {name: "Typ kursu", value: "type"},
  ]

  courseCities = [
    { name: "SOPOT", value: "Sopot"},
    { name: "OLECKO", value: "Olecko"}
  ]
}
