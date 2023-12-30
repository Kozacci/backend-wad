import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {NameValueNull} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormService} from "../../shared/services/form/form.service";
import {formatDateToYYYYMMDD} from "../date-utils.service";

@Component({
  selector: 'app-admin-acceses',
  templateUrl: './admin-acceses.component.html',
  styleUrls: ['./admin-acceses.component.css']
})
export class AdminAccesesComponent {

  participantLastName: string | null = null;
  participantEmail: string | null = null;
  participantPhoneNumber: string | null = null;
  dateFormGroup: FormGroup = new FormGroup({
    dateFrom: new FormControl()
  })
  sortBy: NameValueNull = null;
  accessesList: any[] = [];
  selectedAccesses: any[] = [];
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

  }

  unPassAccess(): void {

  }

  openAccessesModal(): void {
    this.showAccessesModal = true;
  }

  closeAccessesModal(): void {
    this.showAccessesModal = false;
  }

  sortByValues = [
    {name: "Miasto", value: "city"},
    {name: "Data rozpoczęcia kursu", value: "dateFrom"},
    {name: "Typ kursu", value: "type"},
  ]

}
