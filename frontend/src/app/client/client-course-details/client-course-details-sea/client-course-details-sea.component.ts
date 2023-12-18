import {Component} from '@angular/core';
import {CourseDetails} from "../client-course-details.component";
import {ClientCourseDetailsService} from "../client-course-details.service";

@Component({
  selector: 'app-client-course-details-sea',
  templateUrl: './client-course-details-sea.component.html',
  styleUrls: ['./client-course-details-sea.component.css']
})
export class ClientCourseDetailsSeaComponent {

  courseSea: CourseDetails = <CourseDetails>{};

  constructor(
    private readonly clientCourseDetailsService: ClientCourseDetailsService
  ) {
  }

  ngOnInit() {
    this.clientCourseDetailsService.ustawDaneDomyslne(
      this.courseSea,
      'MOTOROWODNY_STERNIK_MORSKI',
      'Morski sternik motorowodny',
      'assets/images/client/overlay/courses-details/szczegoly-morski-sternik.jpg',
      [
        'Uprawnienie do prowadzenie jachtów motorowych po wodach śródlądowych',
        'Uprawnienie do prowadzenia jachtów o długości kadłuba do 18 metrów po wodach morskich',
        'Uprawnienia jachtowego sternika morskiego, jeśli jego posiadacz ma też co najmniej patent żeglarza jachtowego'
      ],
      [
        'Skończone 18 lat',
        'Minimum 200 godzin stażu morskiego uzyskanego w minimum 2 rejsach morskich',
        'Patent sternika motorowodnego nie jest wymagany, ale lepiej zacząć od tego podstawowego patentu i po zdobyciu odpowiedniego stażu pomyśleć o wyższych uprawnieniach',
        'Uiszczenie opłaty za egzamin wg. rozporządzenia Ministra Sportu i Turystyki'
      ],
      "4 dni",
      'Bavaria C54 Yacht (2020)',
      'Dostęp do bazy pytań (e-learning) i egzaminów próbnych',
      'Egzamin w cenie kursu',
      'Certyfikat po ukończeniu szkolenia',
      'Ubezpieczenie NNW i środki ratunkowe na czas zajęć praktycznych',
      '950'
    );
  }

}
