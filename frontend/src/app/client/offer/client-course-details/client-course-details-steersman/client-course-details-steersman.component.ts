import {Component, OnInit} from '@angular/core';
import {CourseDetails} from "../client-course-details.component";
import {ClientCourseDetailsService} from "../client-course-details.service";

@Component({
  selector: 'app-client-course-details-steersman',
  templateUrl: './client-course-details-steersman.component.html',
  styleUrls: ['./client-course-details-steersman.component.css']
})
export class ClientCourseDetailsSteersmanComponent implements OnInit {

  courseSteersman: CourseDetails = <CourseDetails>{};

  constructor(
    private readonly clientCourseDetailsService: ClientCourseDetailsService
  ) {
  }

  ngOnInit() {
    this.clientCourseDetailsService.ustawDaneDomyslne(
      this.courseSteersman,
      'STERNIK_MOTOROWODNY',
      'Sternik Motorowodny',
      'assets/images/client/overlay/courses-details/szczegoly-sternik.jpg',
      [
        'Prowadzenie jachtów motorowych po wodach śródlądowych',
        'Uprawnienie do prowadzenia łodzi motorowych o długości do 12 m po morskich wodach wewnętrznych i pozostałych wodach morskich (2 mile morskie od brzegu w porze dziennej)',
        'Prowadzenie skuterów wodnych bez ograniczeń mocy'
      ],
      [
        'Ukończone 14 lat',
        'Oświadczenie rodziców lub opiekuna prawnego w przypadku niepełnoletniego',
        'Uiszczenie opłaty za egzamin wg. rozporządzenia Ministra Sportu i Turystyki'
      ],
      "3 dni",
      'Nowoczesna Motorówka RiB (2021)',
      'Dostęp do bazy pytań (e-learning)',
      'Dostęp do egzaminów próbnych',
      'Po zaliczeniu egzaminu certyfikat ukończenia szkolenia',
      'Przy poleceniu jednej osoby 50 zł taniej - Przy dwóch 100zł taniej',
      '750'
    );
  }

}
