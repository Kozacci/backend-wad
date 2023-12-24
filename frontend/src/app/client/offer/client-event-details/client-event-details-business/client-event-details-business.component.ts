import { Component } from '@angular/core';
import {EventDetails} from "../client-event-details.component";
import {ClientCourseDetailsService} from "../../client-course-details/client-course-details.service";

@Component({
  selector: 'app-client-event-details-business',
  templateUrl: './client-event-details-business.component.html',
  styleUrls: ['./client-event-details-business.component.css']
})
export class ClientEventDetailsBusinessComponent {

  eventBusiness: EventDetails = <EventDetails>{};

  constructor(
    private readonly clientEventDetailsService: ClientCourseDetailsService
  ) {}

  ngOnInit() {
    this.clientEventDetailsService.ustawDaneDomyslne(
      this.eventBusiness,
      'EVENT_DLA_FIRMY',
      'Rejs z firmą',
      'assets/images/client/overlay/events-details/szczegoly-event-dla-firm.jpg',
      [
        'Półtora godzinny rejs dla uczestników (maksymalna liczba osób to 30)',
        'Atrakcyjne spędzenie czasu na nowoczesnym katamaranie',
        'Lampka wina oraz Katering dla uczestników',
        'Dostępne trzy pokłady (dwa z toaletą)',
        'Możliwość włączenia własnej playlisty z głośników',
        'Pamiątkowe zdjęcia robione lustrzanką'
      ],
      [
        'Prosimy o rezerwację rejsu z conajmniej 72h godzinnym wyprzedzeniem',
        'Intensywny sztorm lub bardzo silny wiatr mogą spowodować odwołanie rejsu o czym niezwłocznie powiadomimy'
      ],
      "2h 30min",
      'Bavaria C53 Business Flote (2020)',
      'Rozpoczęcie rejsu w Sopocie lub Olecku',
      'Możliwość poprowadzenia jachtu',
      'Opieka wykwalifikowanego sternika',
      'Kamizelki ratunkowe oraz ciepłe koce dla komfortu usługi',
      '350zł / osoba',
    );
  }

}
