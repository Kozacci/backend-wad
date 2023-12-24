import { Component } from '@angular/core';
import {ClientCourseDetailsService} from "../../client-course-details/client-course-details.service";
import {EventDetails} from "../client-event-details.component";

@Component({
  selector: 'app-client-event-details-bachelor',
  templateUrl: './client-event-details-bachelor.component.html',
  styleUrls: ['./client-event-details-bachelor.component.css']
})
export class ClientEventDetailsBachelorComponent {

  eventBachelor: EventDetails = <EventDetails>{};

  constructor(
    private readonly clientEventDetailsService: ClientCourseDetailsService
  ) {}

  ngOnInit() {
    this.clientEventDetailsService.ustawDaneDomyslne(
      this.eventBachelor,
      'KAWALERSKI',
      'Wieczór Kawalerski',
      'assets/images/client/overlay/events-details/szczegoly-wieczor-kawalerski.jpg',
      [
        'Półtora godzinny rejs dla uczestników wieczora kawalerskiego (maksymalna liczba osób to 15)',
        'Atrakcyjne spędzenie czasu na nowoczesnym katamaranie',
        'Lampkę Prosecco oraz Katering dla uczestników',
        'Dostępny dolny oraz górny pokład z toaletą',
        'Możliwość wniesienia własnego alkoholu lub pożywienia',
        'Możliwość włączenia własnej playlisty z głośników',
        'Pamiątkowe zdjęcia robione lustrzanką'
      ],
      [
        'Prosimy o rezerwację rejsu z conajmniej 72h godzinnym wyprzedzeniem',
        'Intensywny sztorm lub bardzo silny wiatr mogą spowodować odwołanie rejsu o czym niezwłocznie powiadomimy'
      ],
      "2h 30min",
      'Bavaria C38 Blue Note (2022)',
      'Rozpoczęcie rejsu w Sopocie lub Olecku',
      'Możliwość poprowadzenia jachtu',
      'Opieka wykwalifikowanego sternika',
      'Kamizelki ratunkowe oraz ciepłe koce dla komfortu usługi',
      '250zł / osoba',
    );
  }

}
