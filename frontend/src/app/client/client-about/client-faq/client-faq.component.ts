import { Component } from '@angular/core';

@Component({
  selector: 'app-client-faq',
  templateUrl: './client-faq.component.html',
  styleUrls: ['./client-faq.component.css']
})
export class ClientFaqComponent {

  // TODO DO ZMIANY INFORMACJE OD HUBERTA
  faqs = [
    {
      question: 'Jak zapisać się na kurs?',
      answer: 'Aby zapisać się na konkretny kurs żeglarski, przejdź na zakładkę z ofertą, a następnie wybierz kursy i interesujący cię typ kursu. Na koniec kliknij w przycisk Zapisz się i wybierz interesujący Cię termin, a następnie przejdź dalej. Pamiętaj, że w celu zapisania się na kurs musisz być zalogowany!',
      id: 'collapseOne'
    },
    {
      question: 'Jak zarezerwować rejs?',
      answer: 'Aby zarezerwować rejs, przejdź na zakładkę z ofertą, a następnie wybierz eventy i interesujący cię typ rejsu. Na koniec kliknij w przycisk Rezerwuj, wybierz interesujący Cię termin, liczbę uczestnikow i voille.',
      id: 'collapseTwo'
    },
    {
      question: 'Co w przypadku nagłej zmiany pogody?',
      answer: 'Jeśli zbliżająca się pogoda i warunki nie pozwalają na komfortowy rejs, kontaktujemy się z Państwem najszybciej jak to możliwe (zazwyczaj 24-48h przed), aby poinformować o zaistniałej sytuacji i ewentualnym przełożeniu terminu/lokalizacji rejsu lub zwrotu środków.',
      id: 'collapseThree'
    },
    {
      question: 'Czy mogę zmienić termin rejsu/kursu?',
      answer: 'Oczywiście tak, ale musi to nastąpić conajmniej 2 tygodnie przed startem kursu lub 48h przed rozpoczęciem rejsu.',
      id: 'collapseFour'
    },
    {
      question: 'Skąd wypływamy, gdzie mam się stawić?',
      answer: 'Nasze statki wypływają z przystani LOK w Olecku lub z molo w Sopocie. Zapraszamy w wyznaczone miejsca kilkanaście minut przed terminem wypłynięcia, aby na spokojnie zapoznać się z procedurami wydarzenia.',
      id: 'collapseFive'
    },
    {
      question: 'Jakie ubranie na rejs?',
      answer: 'Tak aby było wygodnie :).',
      id: 'collapseSix'
    },
    {
      question: 'Czy można skakać z jachtu?',
      answer: 'Przy zgodzie sternika i odpowiednich warunkach pogodowych - tak.',
      id: 'collapseSeven'
    },
    {
      question: 'Czy zwierzęta na pokładzie są dozwolone?',
      answer: 'O ile nie są agresywne i są Państwo pewni, że będą w stanie nad nim zapanować to jasne. Warto wziąć ze sobą jednak kamizelke asekuracyjną.',
      id: 'collapseEight'
    },
  ];

}
