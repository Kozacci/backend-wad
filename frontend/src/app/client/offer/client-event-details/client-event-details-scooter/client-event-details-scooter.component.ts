import {Component} from '@angular/core';
import {EventDetails} from "../client-event-details.component";
import {ClientCourseDetailsService} from "../../client-course-details/client-course-details.service";

@Component({
  selector: 'app-client-event-details-scooter',
  templateUrl: './client-event-details-scooter.component.html',
  styleUrls: ['./client-event-details-scooter.component.css']
})
export class ClientEventDetailsScooterComponent {

  eventScooter: EventDetails = <EventDetails>{};

  constructor(
    private readonly clientEventDetailsService: ClientCourseDetailsService
  ) {}

  ngOnInit() {
    this.clientEventDetailsService.ustawDaneDomyslne(
      this.eventScooter,
      'WYNAJEM_SKUTERA',
      'Wynajem skutera wodnego',
      'assets/images/client/overlay/events-details/szczegoly-skuter-wodny.jpg',
      [
        'Wynajęcie skutera wodnego na czas jednej godziny',
        'Efektowne spędzenie czasu na 250-konnym, nowoczesnym skuterze',
        'Możliwość włączenia własnej playlisty z głośnika'
      ],
      [
        'Prosimy o rezerwację skutera z conajmniej 48h godzinnym wyprzedzeniem',
        'Intensywny sztorm lub bardzo silny wiatr mogą spowodować odwołanie możliwości wypożeczenia'
      ],
      "1h 00min",
      'SEA-DOO EXPLORER PRO 170 IDF TECH PACKAGE (2023)',
      'Skutery dostępne w Sopocie lub Olecku',
      'Możliwość podpięcia haku z liną i deską surfingową',
      'Ubezpieczenie w zestawie',
      'Kamizelka ratunkowa w zestawie',
      '250zł / osoba',
    );
  }

}
