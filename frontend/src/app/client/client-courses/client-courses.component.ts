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
        // inne punkty
      ],
      price: '550zł / OSOBA',
      image: 'path_to_image.jpg',
      // inne właściwości kursu
    },
    // inne kursy...
  ];

  constructor(
    private readonly pathService: PathService
  )
  {
  }


  goToDetails(course: any) {
    // Logika nawigacji do szczegółów kursu, np.:
    this.pathService.navigate('/courses-top');
  }

}
