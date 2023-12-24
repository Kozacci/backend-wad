import {Component, OnInit} from '@angular/core';
import {CourseDetails} from "../client-course-details.component";
import {ClientCourseDetailsService} from "../client-course-details.service";

@Component({
  selector: 'app-client-course-details-sailor',
  templateUrl: './client-course-details-sailor.component.html',
  styleUrls: ['./client-course-details-sailor.component.css']
})
export class ClientCourseDetailsSailorComponent implements OnInit {

  courseSailor: CourseDetails = <CourseDetails>{};

  constructor(
    private readonly clientCourseDetailsService: ClientCourseDetailsService
  ) {
  }

  ngOnInit() {
    this.clientCourseDetailsService.ustawDaneDomyslne(
      this.courseSailor,
      'ZEGLARZ_JACHTOWY',
      'Żeglarz jachtowy',
      'assets/images/client/overlay/courses-details/szczegoly-zeglarz-jachtowy.jpg',
      [
        'Uprawnienie do prowadzenie jachtów motorowych po wodach śródlądowych',
        'Uprawnienie do prowadzenia jachtów o długości kadłuba do 12 m po morskich wodach wewnętrznych oraz pozostałych wodach morskich w strefie do 2 Mm od brzegu, w porze dziennej.',
      ],
      [
        'Ukończone 14 lat',
        'Oświadczenie rodziców lub opiekuna prawnego w przypadku niepełnoletniego',
        'Uiszczenie opłaty za egzamin wg. rozporządzenia Ministra Sportu i Turystyki'
      ],
      "3 dni",
      'Jachtownik 3000 (2018)',
      'Dostęp do bazy pytań (e-learning) i egzaminów próbnych',
      'Egzamin w cenie kursu',
      'Certyfikat po ukończeniu szkolenia',
      'Ubezpieczenie NNW i środki ratunkowe na czas zajęć praktycznych',
      '750'
    );
  }
}
