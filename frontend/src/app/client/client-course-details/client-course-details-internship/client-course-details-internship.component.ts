import {Component} from '@angular/core';
import {CourseDetails} from "../client-course-details.component";
import {ClientCourseDetailsService} from "../client-course-details.service";

@Component({
  selector: 'app-client-course-details-internship',
  templateUrl: './client-course-details-internship.component.html',
  styleUrls: ['./client-course-details-internship.component.css']
})
export class ClientCourseDetailsInternshipComponent {

  courseInternship: CourseDetails = <CourseDetails>{};

  constructor(
    private readonly clientCourseDetailsService: ClientCourseDetailsService
  ) {
  }

  ngOnInit() {
    this.clientCourseDetailsService.ustawDaneDomyslne(
      this.courseInternship,
      'REJSY_STAZOWE',
      'Rejsy stażowe',
      'assets/images/client/overlay/courses-details/szczegoly-rejsy-stazowe.jpg',
      [
        'Pod okiem kapitana uczysz się obsługi jachtu i zdobywasz staż morski, który poświadczony jest opinią z rejsu dla PZŻ i/lub książeczki żeglarskiej',
        'Nie wymagamy patentu i doświadczenia',
        'Na takim rejsie zdobywasz opływanie, doświadczenie i uczysz się żeglarstwa oraz nabierasz nawyków związanych z dobrą praktyką morską',
      ],
      [
        'Ukończone 18 lat',
        'Oświadczenie rodziców lub opiekuna prawnego w przypadku niepełnoletniego',
      ],
      "3 dni",
      'Bavaria 49/Bavaria 54 (2021/2022)',
      'W cenie wyżywienie, ubezpieczenie jednostki, postój w portach oraz paliwo',
      'Materiały dydaktyczne przesyłane drogą elektroniczną przed rejsem (rejsy szkoleniowe)',
      'Uzyskanie opinii określającej ilość przepływanych godzin i mil oraz odwiedzane porty',
      'Możliwość uzyskania certyfikatu ISSA potwierdzającego nabyte umiejętności i doświadczenie',
      '450'
    );
  }

}
