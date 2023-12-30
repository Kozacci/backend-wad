import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {
  NameValueNull,
  ParticipantCourseEntityDTO,
  ParticipantCourseFilterDTO,
  ParticipantCourseUpdateDTO
} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormService} from "../../shared/services/form/form.service";
import {formatDateToYYYYMMDD} from "../date-utils.service";

@Component({
  selector: 'app-admin-acceses',
  templateUrl: './admin-accesses.component.html',
  styleUrls: ['./admin-accesses.component.css']
})
export class AdminAccessesComponent {

  participantLastName: string | null = null;
  participantEmail: string | null = null;
  participantPhoneNumber: string | null = null;
  dateFormGroup: FormGroup = new FormGroup({
    dateFrom: new FormControl()
  })
  sortBy: NameValueNull = null;
  accessesList: ParticipantCourseFilterDTO[] = [];
  selectedAccesses: ParticipantCourseFilterDTO[] = [];
  showAccessesModal: boolean = false;

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService,
              protected formService: FormService) {
  }

  findAccesses(): void {
    this.restClient.getCoursesByParticipantIdAndFilters(
      undefined,
      undefined,
      undefined,
      null,
      null,
      this.sortBy?.value,
      this.participantEmail,
      this.participantLastName,
      formatDateToYYYYMMDD(this.dateFormGroup.value.dateFrom),
      undefined,
      this.participantPhoneNumber
      ).subscribe(response => {
        this.accessesList = response;
      }, error => {
        this.responseHandlerService.handleErrorsPtoasts(error);
      })
  }

  passAccess(): void {
    let participantCourseUpdateDTO = <ParticipantCourseUpdateDTO>{
      participantCourseIds: this.selectedAccesses.map(pcf => pcf.participantCourseId),
      hasAccess: true
    }
    this.restClient.updateCoursePassing(participantCourseUpdateDTO).subscribe(response => {
      this.updateAccesses(response, true);
      this.responseHandlerService.showSuccessPToast("Zaliczenia", "Zaktualizowano zaliczenia.");
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  unPassAccess(): void {
    let participantCourseUpdateDTO = <ParticipantCourseUpdateDTO>{
      participantCourseIds: this.selectedAccesses.map(pcf => pcf.participantCourseId),
      hasAccess: false
    }
    this.restClient.updateCoursePassing(participantCourseUpdateDTO).subscribe(response => {
      this.updateAccesses(response, false);
      this.responseHandlerService.showSuccessPToast("Zaliczenia", "Zaktualizowano zaliczenia.");
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
  }

  private updateAccesses(response: ParticipantCourseEntityDTO[], hasAccess: boolean) {
    let changedRecordsIds = response.map(dto => dto.participantCourseId);
    this.accessesList.forEach(record => {
      if (changedRecordsIds.includes(record.participantCourseId)) {
        record.hasAccess = hasAccess;
      }
    })
  }

  openAccessesModal(): void {
    if (this.selectedAccesses.length == 0) {
      this.responseHandlerService.showWarningPToast("Dostępy", "Najpierw wybierz kursantów, którym chcesz dać dostęp do nauki.");
      return;
    }

    this.showAccessesModal = true;
  }

  closeAccessesModal(): void {
    this.showAccessesModal = false;
  }

  sortByValues = [
    {name: "Posiada dostęp", value: "hasAccess"},
    {name: "Data rozpoczęcia kursu", value: "dateFrom"},
    {name: "Data zakończenia kursu", value: "dateTo"},
    {name: "Nazwisko kursanta", value: "lastName"},
    {name: "Email kursanta", value: "email"},
  ]
}
