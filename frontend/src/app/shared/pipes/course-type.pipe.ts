import {Pipe, PipeTransform} from '@angular/core';
import {CourseType} from "../dto";

@Pipe({
  name: 'courseType'
})
export class CourseTypePipe implements PipeTransform {

  transform(value: CourseType): string {
    switch (value) {
      case CourseType.STERNIK_MOTOROWODNY:
        return 'Sternik motorowodny';
      case CourseType.JACHTOWY_STERNIK_MORSKI:
        return 'Jachtowy sternik morski';
      case CourseType.MOTOROWODNY_STERNIK_MORSKI:
        return 'Motorowodny sternik morski';
      case CourseType.ZEGLARZ_JACHTOWY:
        return 'Żeglarz jachtowy';
      case CourseType.WARSZTATY_NAWIGACYJNE:
        return 'Warsztaty nawigacyjne';
      case CourseType.REJSY_STAZOWE:
        return 'Rejsy stażowe';
      default:
        return 'Nie wskazano';
    }
  }

}
