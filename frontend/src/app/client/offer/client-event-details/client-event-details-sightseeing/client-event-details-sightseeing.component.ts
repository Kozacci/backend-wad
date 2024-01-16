import {Component} from '@angular/core';
import {EventDetails} from "../client-event-details.component";
import {ClientCourseDetailsService} from "../../client-course-details/client-course-details.service";

@Component({
  selector: 'app-client-event-details-sightseeing',
  templateUrl: './client-event-details-sightseeing.component.html',
  styleUrls: ['./client-event-details-sightseeing.component.css']
})
export class ClientEventDetailsSightseeingComponent {

  eventSightseeing: EventDetails = <EventDetails>{};

  constructor(
    private readonly clientEventDetailsService: ClientCourseDetailsService
  ) {}

  ngOnInit() {
    this.clientEventDetailsService.ustawDaneDomyslne(
      this.eventSightseeing,
      'REJS_WIDOKOWY',
      'Rejs widokowy',
      'assets/images/client/overlay/events-details/szczegoly-rejs-widokowy.jpg',
      [
        'Półtora godzinny rejs dla uczestników (maksymalna liczba osób to 10)',
        'Atrakcyjne spędzenie czasu na wodzie',
        'Dostępna toaleta na pokładzie',
        'Możliwość włączenia własnej playlisty z głośników'
      ],
      [
        'Prosimy o rezerwację rejsu z conajmniej 72h godzinnym wyprzedzeniem',
        'Intensywny sztorm lub bardzo silny wiatr mogą spowodować odwołanie rejsu o czym niezwłocznie powiadomimy'
      ],
      "1h 30min",
      'Bavaria E21 Mariner Flote (2021)',
      'Rozpoczęcie rejsu w Sopocie lub Olecku',
      'Możliwość poprowadzenia jachtu',
      'Opieka wykwalifikowanego sternika',
      'Kamizelki ratunkowe oraz ciepłe koce dla komfortu usługi',
      '175zł / osoba',
    );
  }

}
