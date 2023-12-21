import { Pipe, PipeTransform } from '@angular/core';
import {EventCity} from "../dto";

@Pipe({
  name: 'eventCity'
})
export class EventCityPipe implements PipeTransform {

  transform(value: EventCity): string {
    switch (value) {
      case EventCity.SOPOT:
        return 'Sopot';
      case EventCity.GDANSK:
        return 'Gdańsk';
      case EventCity.OLECKO:
        return 'Olecko';
      default:
        return 'Nie wskazano';
    }
  }

}
