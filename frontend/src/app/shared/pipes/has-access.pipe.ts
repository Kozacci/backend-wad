import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'hasAccess'
})
export class HasAccessPipe implements PipeTransform {

  transform(value: boolean): string {
    if (value) {
      return "Posiada dostęp";
    }
    else if (!value) {
      return "Nie posiada dostępu";
    }
    else return "Błędna wartość"
  }

}
