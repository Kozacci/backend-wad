import {Injectable} from "@angular/core";
import {MessageService} from "primeng/api";
import {ErrorDTO, GroupedErrorDTO} from "../dto";

@Injectable({providedIn: 'root'})
export class HttpResponseHandlerService {

  constructor(private messageService: MessageService) {
  }

  // httpError is an object containing error, header, message, name, status, statusText, url
  handleErrorsPtoasts(httpError: any) {
    this.getErrorDTOS(httpError)
      .forEach(errorDTO => this.showErrorPToast(errorDTO.fieldName, errorDTO.message))
  }

  private getErrorDTOS(httpError: any) {
    return Object.values(<ErrorDTO>httpError.error)
      .map(error => <ErrorDTO>{fieldName: error.fieldName, message: error.message});
  }

  private groupErrorsByField(errors: ErrorDTO[]): GroupedErrorDTO[] {
    const groupedErrors: { [key: string]: string[] } = {};

    errors.forEach(error => {
      if (!groupedErrors[error.fieldName]) {
        groupedErrors[error.fieldName] = [];
      }
      groupedErrors[error.fieldName].push(error.message);
    });

    return Object.keys(groupedErrors)
      .map(fieldName => <GroupedErrorDTO>{fieldName: fieldName, messages:groupedErrors[fieldName]});
  }

  getErrorsBelowInputs(httpError: any): GroupedErrorDTO[] {
    const errorDtos = this.getErrorDTOS(httpError);
    return this.groupErrorsByField(errorDtos)
  }

  extractInputErrorMessages(groupedErrorsDTO: GroupedErrorDTO[], fieldName: string): string[] {
    return Object.values(groupedErrorsDTO)
      .filter(groupedErrorDTO => groupedErrorDTO.fieldName === fieldName)
      .flatMap(groupedErrorsDTO => groupedErrorsDTO.messages);
  }

  groupedErrorsContainsFieldName(groupedErrorsDTO: GroupedErrorDTO[], fieldName:string): boolean {
    return Object.values(groupedErrorsDTO)
      .filter(groupedErrorDTO => groupedErrorDTO.fieldName === fieldName)
      .length > 0;
  }

  showErrorPToast(summary: string, detail: string): void {
    this.messageService.add({life: 4000, severity: 'error', summary: summary, detail: detail});
  }

  showSuccessPToast(summary: string, detail: string): void {
    this.messageService.add({life: 4000, severity: 'success', summary: summary, detail: detail})
  }

  showInfoPToast(summary: string, detail: string): void {
    this.messageService.add({life: 4000, severity: 'info', summary: summary, detail: detail})
  }

  showWarningPToast(summary: string, detail: string): void {
    this.messageService.add({life: 4000, severity: 'warning', summary: summary, detail: detail})
  }

}
