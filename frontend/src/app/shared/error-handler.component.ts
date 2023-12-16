import {Injectable} from "@angular/core";
import {MessageService} from "primeng/api";
import {ErrorDTO} from "./dto";

@Injectable({providedIn: 'root'})
export class ErrorHandlerService {

  constructor(private messageService: MessageService) {
  }

  handleErrorsPToasts(errors: ErrorDTO[]) {
    Object.values(errors).forEach(err => this.error(err.fieldName, err.message))
  }

  error(detail: string, summary: string) {
    this.messageService.add({life: 4000, severity: 'error',  detail: detail, summary: summary});
  }
}
