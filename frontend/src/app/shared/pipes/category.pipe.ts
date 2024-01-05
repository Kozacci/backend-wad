import {Pipe, PipeTransform} from '@angular/core';
import {Category} from "../dto";

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(value: Category): string {
    switch (value) {
      case Category.PRZEPISY:
        return 'Przepisy';
      case Category.PODSTAWY_LOCJI:
        return 'Podstawy locji';
      case Category.WIADOMOSCI_Z_METEOROLOGII:
        return 'Wiadomości z zakresu meteorologii';
      case Category.PODSTAWY_BUDOWY_JACHTOW:
        return 'Podstawy budowy jachtów motorowodnych';
      case Category.SILNIKI_I_UKLADY_NAPEDOWE:
        return 'Silniki i układy napędowe';
      case Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO:
        return 'Wiadomości z zakresu ratownictwa wodnego';
      case Category.POMOCE_NAWIGACYJNE:
        return 'Pomoce nawigacyjne';
      case Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM:
        return 'Ochrona wód przed zanieczyszczeniem';
      case Category.PODSTAWOWE_PRZEPISY_PRAWA:
        return 'Podstawowe przepisy prawa drogi na morskich i śródlądowych drogach wodnych';
      case Category.TEORIA_ZEGLOWANIA:
        return 'Teoria żeglowania';
      default:
        return 'Nie wskazano';
    }
  }

}
