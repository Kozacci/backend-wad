import {Pipe, PipeTransform} from '@angular/core';
import {CourseCity} from "../dto";

@Pipe({
  name: 'courseCity'
})
export class CourseCityPipe implements PipeTransform {

  transform(value: CourseCity): string {
    switch (value) {
      case CourseCity.SOPOT:
        return 'Sopot';
      case CourseCity.OLECKO:
        return 'Olecko';
      default:
        return 'Nie wskazano';
    }
  }

}
