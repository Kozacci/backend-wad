import {Injectable} from '@angular/core';
import {Category, ParticipantCourseEntityDTO} from "../../shared/dto";

@Injectable({
  providedIn: 'root'
})
export class ClientElearningService {

  constructor() { }

  getCategoriesByCourseType(course: ParticipantCourseEntityDTO) {
    const categories: string[] = [];
    // TODO -- PROPER CATEGORIES FOR ANY COURSE TYPE
    switch(course.courseType) {
      case 'Sternik motorowodny':
        categories.push(
          Category.PRZEPISY,
          Category.PODSTAWY_LOCJI,
          Category.TEORIA_ZEGLOWANIA,
          Category.PODSTAWOWE_PRZEPISY_PRAWA,
          Category.SILNIKI_I_UKLADY_NAPEDOWE,
          Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO
        );
        return categories;
      case 'Jachtowy sternik morski':
        categories.push(
          Category.PRZEPISY,
          Category.PODSTAWY_LOCJI,
          Category.TEORIA_ZEGLOWANIA,
          Category.PODSTAWOWE_PRZEPISY_PRAWA,
          Category.SILNIKI_I_UKLADY_NAPEDOWE,
          Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO,
          Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
          Category.POMOCE_NAWIGACYJNE
        );
        return categories;
      case 'Motorowodny sternik morski':
        categories.push(
          Category.PRZEPISY,
          Category.PODSTAWY_LOCJI,
          Category.TEORIA_ZEGLOWANIA,
          Category.PODSTAWOWE_PRZEPISY_PRAWA,
          Category.SILNIKI_I_UKLADY_NAPEDOWE,
          Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO,
          Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
          Category.POMOCE_NAWIGACYJNE,
          Category.WIADOMOSCI_Z_METEOROLOGII,
          Category.PODSTAWY_BUDOWY_JACHTOW
        );
        return categories;
      case 'Żeglarz jachtowy':
        categories.push(
          Category.PRZEPISY,
          Category.TEORIA_ZEGLOWANIA,
          Category.PODSTAWOWE_PRZEPISY_PRAWA,
          Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO,
          Category.PODSTAWY_BUDOWY_JACHTOW,
          Category.POMOCE_NAWIGACYJNE,
          Category.WIADOMOSCI_Z_METEOROLOGII
        );
        return categories;
      default:
        categories.push(
          Category.PRZEPISY,
          Category.TEORIA_ZEGLOWANIA,
          Category.PODSTAWOWE_PRZEPISY_PRAWA,
          Category.SILNIKI_I_UKLADY_NAPEDOWE,
          Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO
        );
        return categories;
    }
  }

}
