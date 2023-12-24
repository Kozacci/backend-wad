import { Component } from '@angular/core';
import {EventDetails} from "../client-event-details.component";
import {ClientCourseDetailsService} from "../../client-course-details/client-course-details.service";

@Component({
  selector: 'app-client-event-details-bachelorette',
  templateUrl: './client-event-details-bachelorette.component.html',
  styleUrls: ['./client-event-details-bachelorette.component.css']
})
export class ClientEventDetailsBacheloretteComponent {

  eventBachelorette: EventDetails = <EventDetails>{};

  constructor(
    private readonly clientEventDetailsService: ClientCourseDetailsService
  ) {}

  ngOnInit() {
    this.clientEventDetailsService.ustawDaneDomyslne(
      this.eventBachelorette,
      'PANIENSKI',
      'Wieczór Panieński',
      'assets/images/client/overlay/events-details/szczegoly-wieczor-panienski.jpeg',
      [
        'Półtora godzinny rejs dla uczestniczek wieczora panieńskiego (maksymalna liczba osób to 15)',
        'Atrakcyjne spędzenie czasu na nowoczesnym katamaranie',
        'Lampka Prosecco oraz Katering dla uczestników',
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
      'Bavaria R46 Violet Note (2022)',
      'Rozpoczęcie rejsu w Sopocie lub Olecku',
      'Możliwość poprowadzenia jachtu',
      'Opieka wykwalifikowanego sternika',
      'Kamizelki ratunkowe oraz ciepłe koce dla komfortu usługi',
      '250zł / osoba',
    );
  }

}
