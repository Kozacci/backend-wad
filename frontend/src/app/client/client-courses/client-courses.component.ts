import {Component} from '@angular/core';
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-courses',
  templateUrl: './client-courses.component.html',
  styleUrls: ['./client-courses.component.css']
})
export class ClientCoursesComponent {

  courses = [
    {
      title: 'STEROWNIK MOTOROWODNY',
      location: 'Sopot | Olecko',
      includes: [
        'Szkolenie on-line (e-learning)',
        'Szkolenie stacjonarne',
        'Egzamin kończący',
        'Certyfikat ukończenia szkolenia'
      ],
      price: '750zł / OSOBA',
      image: 'assets/images/client/overlay/courses/course-1.png',
    },
    {
      title: 'JACHTOWY STERNIK MORSKI',
      location: 'Sopot | Olecko',
      includes: [
        'Szkolenie on-line (e-learning)',
        'Szkolenie stacjonarne',
        'Egzamin kończący',
        'Certyfikat ukończenia szkolenia'
      ],
      price: '850zł / OSOBA',
      image: 'assets/images/client/overlay/courses/course-2.png',
    },
    {
      title: 'MORSKI STERNIK MOTOROWODNY',
      location: 'Sopot | Olecko',
      includes: [
        'Szkolenie on-line (e-learning)',
        'Szkolenie stacjonarne',
        'Egzamin kończący',
        'Certyfikat ukończenia szkolenia'
      ],
      price: '950zł / OSOBA',
      image: 'assets/images/client/overlay/courses/course-3.png',
    },
    {
      title: 'ŻEGLARZ JACHTOWY',
      location: 'Sopot | Olecko',
      includes: [
        'Szkolenie on-line (e-learning)',
        'Szkolenie stacjonarne',
        'Egzamin kończący',
        'Certyfikat ukończenia szkolenia'
      ],
      price: '750zł / OSOBA',
      image: 'assets/images/client/overlay/courses/course-4.png',
    },
    {
      title: 'WARSZTATY NAWIGACYJNE',
      location: 'Sopot | Olecko',
      includes: [
        'Szkolenie stacjonarne',
        'Certyfikat ukończenia warsztatu'
      ],
      price: '350zł / OSOBA',
      image: 'assets/images/client/overlay/courses/course-5.png',
    },
    {
      title: 'REJSY STAŻOWE',
      location: 'Sopot | Olecko',
      includes: [
        'Szkolenie stacjonarne',
        'Certyfikat ukończenia stażu'
      ],
      price: '450zł / OSOBA',
      image: 'assets/images/client/overlay/courses/course-6.png',
    },
  ];

  constructor(
    private readonly pathService: PathService
  )
  {
  }


  goToCourseDetails(course: any) {
    // Logika nawigacji do szczegółów kursu, np.:
    this.pathService.navigate('/courses-top');
  }

}
