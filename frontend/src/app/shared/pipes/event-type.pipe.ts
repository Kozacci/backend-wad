import { Pipe, PipeTransform } from '@angular/core';
import {EventType} from "../dto";

@Pipe({
  name: 'eventType'
})
export class EventTypePipe implements PipeTransform {

  transform(value: EventType): string {
    switch (value) {
      case EventType.EVENT_DLA_FIRMY:
        return 'Event dla firmy';
      case EventType.KAWALERSKI:
        return 'Kawalerski';
      case EventType.PANIENSKI:
        return 'Panieński';
      case EventType.REJS_WIDOKOWY:
        return 'Rejs widokowy';
      case EventType.WYNAJEM_SKUTERA:
        return 'Wynajem skutera wodnego';
      default:
        return 'Nie wskazano';
    }
  }

}
