import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'isCoursePassed'
})
export class IsCoursePassedPipe implements PipeTransform {

  transform(value: boolean): string {
    if (value) {
      return "Zaliczony";
    } else if (!value) {
      return "Niezaliczony";
    } else {
      return "Nie wskazano";
    }
  }

}
