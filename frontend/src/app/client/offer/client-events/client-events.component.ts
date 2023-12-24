import {Component} from '@angular/core';
import {PathService} from "../../../shared/services/path.service";

@Component({
  selector: 'app-client-events',
  templateUrl: './client-events.component.html',
  styleUrls: ['../client-courses/client-courses.component.css']
})
export class ClientEventsComponent {

  // TODO - event details general component and specific components

  events = [
    {
      title: 'WIECZÓR PANIEŃSKI',
      location: 'Sopot | Olecko',
      includes: [
        'Dobra Muzyka',
        'Lampka Prosecco + Katering',
        'Gadżety rozrywkowe',
        'Sesja fotograficzna'
      ],
      price: '250zł / OSOBA',
      image: 'assets/images/client/overlay/events/event-1.png',
      url: '/oferta/eventy/wieczor-panienski'
    },
    {
      title: 'ZACHÓD SŁOŃCA',
      location: 'Sopot | Olecko',
      includes: [
        'Lampka wina do wyboru',
        'Katering (żywność)'
      ],
      price: '175zł / OSOBA',
      image: 'assets/images/client/overlay/events/event-2.png',
      url: '/oferta/eventy/rejs-o-zachodzie'
    },
    {
      title: 'WIECZÓR KAWALERSKI',
      location: 'Sopot | Olecko',
      includes: [
        'Dobra Muzyka',
        'Lampka Prosecco + Katering',
        'Gadżety rozrywkowe',
        'Sesja fotograficzna'
      ],
      price: '250zł / OSOBA',
      image: 'assets/images/client/overlay/events/event-3.png',
      url: '/oferta/eventy/wieczor-kawalerski'
    },
    {
      title: 'REJS WIDOKOWY',
      location: 'Sopot',
      includes: [
        'Lampka wina do wyboru',
        'Katering (żywność)'
      ],
      price: '150zł / OSOBA',
      image: 'assets/images/client/overlay/events/event-4.png',
      url: '/oferta/eventy/rejs-widokowy'
    },
    {
      title: 'EVENT DLA FIRMY',
      location: 'Sopot | Olecko',
      includes: [
        'Lampka wina do wyboru',
        'Katering (żywność)',
        'Sesja fotograficzna'
      ],
      price: '250zł / OSOBA',
      image: 'assets/images/client/overlay/events/event-5.png',
      url: '/oferta/eventy/event-dla-firmy'
    },
    {
      title: 'WYNAJEM SKUTERA',
      location: 'Sopot | Olecko',
      includes: [
        'Kamizelka wypornościowa',
        'Instruktaż sprzętu'
      ],
      price: '250zł / 60min',
      image: 'assets/images/client/overlay/events/event-6.png',
      url: '/oferta/eventy/wynajem-skutera'
    },
  ];

  constructor(
    private readonly pathService: PathService
  ) {}


  goToCourseDetails(courseUrl: string) {
    this.pathService.navigate(courseUrl);
  }


}
