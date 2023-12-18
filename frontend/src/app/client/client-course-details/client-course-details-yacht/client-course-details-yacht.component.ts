import {Component} from '@angular/core';
import {CourseDetails} from "../client-course-details.component";
import {ClientCourseDetailsService} from "../client-course-details.service";

@Component({
  selector: 'app-client-course-details-yacht',
  templateUrl: './client-course-details-yacht.component.html',
  styleUrls: ['./client-course-details-yacht.component.css']
})
export class ClientCourseDetailsYachtComponent {

  courseYacht: CourseDetails = <CourseDetails>{};

  constructor(
    private readonly clientCourseDetailsService: ClientCourseDetailsService
  ) {
  }

  ngOnInit() {
    this.clientCourseDetailsService.ustawDaneDomyslne(
      this.courseYacht,
      'JACHTOWY_STERNIK_MORSKI',
      'Jachtowy sternik morski',
      'assets/images/client/overlay/courses-details/szczegoly-jachtowy-sternik.jpg',
      [
        'Uprawnienie do prowadzenia jachtów żaglowych o długości do 18 m kadłuba po wodach morskich',
        'Uprawnienie do prowadzenia jachtów morskich po wodach morskich (zewnętrznych i wewnętrznych)'
      ],
      [
        'Ukończone 18 lat',
        'Uiszczenie opłaty za egzamin wg. rozporządzenia Ministra Sportu i Turystyki'
      ],
      "5 dni",
      'Bavaria C38 Blue Note (2022)',
      'Dostęp do bazy pytań (e-learning)',
      'Dostęp do egzaminów próbnych',
      'Po zaliczeniu egzaminu certyfikat ukończenia szkolenia',
      'Godziny wypływane podczas szkolenia zaliczają się do stażu wymaganego do uzyskania uprawnień jachtowego sternika morskiego',
      '850'
    );
  }

}
