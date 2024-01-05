import {Pipe, PipeTransform} from '@angular/core';
import {CourseStatus} from "../dto";

@Pipe({
  name: 'courseStatus'
})
export class CourseStatusPipe implements PipeTransform {

  transform(value: CourseStatus): string {
    switch (value) {
      case CourseStatus.ROZPOCZETY:
        return "Rozpoczęty";
      case CourseStatus.NIEROZPOCZETY:
        return "Nierozpoczęty"
      case CourseStatus.ZAKONCZONY:
        return "Zakończony"
      default:
        return 'Nie wskazano';
    }
  }

}
