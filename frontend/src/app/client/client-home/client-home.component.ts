import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../shared/services/auth/auth.service";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.css']
})
export class ClientHomeComponent implements OnInit {

  courses = [
    {
      name: 'Sternik motorowodny',
      image: 'assets/images/client/overlay/courses/course-1.png',
      cost: 'od 750 zł za osobę',
      status: 'Popularne',
      url: '/oferta/kursy/sternik-motorowodny'
    },
    {
      name: 'Jachtowy sternik morski',
      image: 'assets/images/client/overlay/courses/course-2.png',
      cost: 'od 850 zł za osobę',
      status: 'Nowość',
      url: '/oferta/kursy/jachtowy-sternik'
    },
    {
      name: 'Morski sternik motorowodny',
      image: 'assets/images/client/overlay/courses/course-3.png',
      cost: 'od 950 zł za osobę',
      status: 'Nowość',
      url: '/oferta/kursy/morski-sternik'
    },
    {
      name: 'Żeglarz jachtowy',
      image: 'assets/images/client/overlay/courses/course-4.png',
      cost: 'od 650 zł za osobę',
      status: 'Popularne',
      url: '/oferta/kursy/zeglarz-jachtowy'
    },
    {
      name: 'Warsztaty nawigacyjne',
      image: 'assets/images/client/overlay/courses/course-5.png',
      cost: 'od 350 zł za osobę',
      status: 'Warsztat',
      url: '/oferta/kursy/warsztaty-nawigacyjne'
    },
    {
      name: 'Rejsy stażowe',
      image: 'assets/images/client/overlay/courses/course-6.png',
      cost: 'od 450 zł za osobę',
      status: 'Staż',
      url: '/oferta/kursy/rejsy-stazowe'
    },
  ];

  events = [
    {
      name: 'Wieczór panieński',
      image: 'assets/images/client/overlay/events/event-1.png',
      cost: 'od 350 zł za osobę',
      status: 'Popularne',
      url: '/oferta/eventy/wieczor-panienski'
    },
    {
      name: 'Wieczór kawalerski',
      image: 'assets/images/client/overlay/events/event-3.png',
      cost: 'od 350 zł za osobę',
      status: 'Nowość',
      url: '/oferta/eventy/wieczor-kawalerski'
    },
    {
      name: 'Rejs widokowy',
      image: 'assets/images/client/overlay/events/event-x.jpg',
      cost: 'od 250 zł za osobę',
      status: 'Popularne',
      url: '/oferta/eventy/rejs-widokowy'
    },
    {
      name: 'Event dla firmy',
      image: 'assets/images/client/overlay/events/event-5.png',
      cost: 'od 350 zł za osobę',
      status: 'Warsztat',
      url: '/oferta/eventy/event-dla-firmy'
    },
    {
      name: 'Wynajem skutera',
      image: 'assets/images/client/overlay/events/event-6.png',
      cost: 'od 200 zł za osobę',
      status: 'Najem',
      url: '/oferta/eventy/wynajem-skutera'
    },
  ];

  responsiveOptionsForCarousel: any = [
    {
      breakpoint: '1220px',
      numVisible: 2,
      numScroll: 2
    },
    {
      breakpoint: '1100px',
      numVisible: 1,
      numScroll: 1
    }
  ];

  reviews = [
    {
      stars: 5,
      name: "Paweł Żytnowski",
      date: "19/07/2021",
      content: "Super profesjonalne podejście, na luzie ale konkretnie. Zdecydowanie polecam Huberta i jego Team! 🚤🚤"
    },
    {
      stars: 5,
      name: "Mikołaj Graczyk",
      date: "25/10/2020",
      content: "Bardzo dobra firma , dobrze szkolą i przygotowują na kurs sternika motorowodnego , a ja sie ciesze z zdanego egzaminu (25.10.2020)"
    },
    {
      stars: 5,
      name: "Marcin Frej",
      date: "21/07/2019",
      content: "Świeżo po ukończeniu kursu sternika motorowodnego (19/21-07-2019) Z całego serca polecam kurs i instruktora"
    },
    {
      stars: 5,
      name: "Andrzej Szewczak",
      date: "25/08/2018",
      content: "Co tu dużo pisać 🙂 zapisywać się!!! Najlepsze kursy w Polsce 😉"
    },
    {
      stars: 5,
      name: "Milena Hołdyńska",
      date: "25/08/2018",
      content: "Bardzo solidnie i rzetelnie przekazana wiedza, kurs odbył się szybko i bezproblemowo. Kontakt z kursantem na wysokim poziomie a na każde z pytań pojawiała się jasna odpowiedź."
    }
  ];

  constructor(
    private readonly authService: AuthService,
    public readonly pathService: PathService)
  {}

  ngOnInit() {
    this.authService.isLogged();
  }

}
