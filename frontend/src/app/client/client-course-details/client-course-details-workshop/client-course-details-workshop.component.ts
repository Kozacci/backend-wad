import {Component} from '@angular/core';
import {CourseDetails} from "../client-course-details.component";
import {ClientCourseDetailsService} from "../client-course-details.service";

@Component({
  selector: 'app-client-course-details-workshop',
  templateUrl: './client-course-details-workshop.component.html',
  styleUrls: ['./client-course-details-workshop.component.css']
})
export class ClientCourseDetailsWorkshopComponent {

  courseWorkshop: CourseDetails = <CourseDetails>{};

  constructor(
    private readonly clientCourseDetailsService: ClientCourseDetailsService
  ) {
  }

  ngOnInit() {
    this.clientCourseDetailsService.ustawDaneDomyslne(
      this.courseWorkshop,
      'WARSZTATY_NAWIGACYJNE',
      'Warsztaty nawigacyjne',
      'assets/images/client/overlay/courses-details/szczegoly-warsztaty-nawigacyjne.jpg',
      [
        'Szkolenie praktyczne na wodach Zatoki Gdańskiej, na bogato wyposażonym nawigacyjnie jachcie',
        'Warsztaty na jachcie, gdzie ćwiczenia wykonujemy „na żywo” na wodach Zatoki Gdańskiej / Jeziora Olecko',
        'Poznasz wiele bardzo użytecznych funkcji ploterów GPS, programów nawigacyjnych, a także obsługi radaru i wykorzystania go dla celów nawigacyjnych',
        'Nauka urządzeń: AIS, NAVTEX, VHF/MF/HF+DSC, GMDSS',
        'Duża dawka wiedzy wzbogacona praktycznymi wskazówkami doświadczonego instruktora niewątpliwie wzbogacą Twoje nawigacyjne umiejętności',
        'Na deser praktyczna powtórka nawigowania klasycznego w oparciu o światła, mapę i spis świateł oraz radar',
      ],
      [
        'Ukończone 18 lat',
        'Oświadczenie rodziców lub opiekuna prawnego w przypadku niepełnoletniego',
      ],
      "2 dni",
      'Bavaria 49/Bavaria 54 (2021/2022)',
      'Omawiane zagadnienia utrwalamy ćwiczeniami',
      'Pracujemy na autentycznych pomocach nawigacyjnych',
      'Dodatkowo każdy uczestnik otrzyma mapę szkoleniową na własność',
      'Możliwość uzyskania dyplomu ukończenia warsztatów',
      '350'
    );
  }

}
