import {Component} from '@angular/core';
import {Category, QuestionFilterDTO} from "../../shared/dto";

@Component({
  selector: 'app-admin-questions',
  templateUrl: './admin-questions.component.html',
  styleUrls: ['./admin-questions.component.css']
})
export class AdminQuestionsComponent {

  questions: QuestionFilterDTO[] = [
    {
      id: 1,
      content: "Pierwsze pytanie...",
      category: Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
      firstAnswer: "Odpowiedź A",
      secondAnswer: "Odpowiedź B",
      thirdAnswer: "Odpowiedź C"
    },
    {
      id: 2,
      content: "Drugie pytanie...",
      category: Category.PODSTAWOWE_PRZEPISY_PRAWA,
      firstAnswer: "Odpowiedź A",
      secondAnswer: "Odpowiedź B",
      thirdAnswer: "Odpowiedź C"
    },
    {
      id: 3,
      content: "Trzecie pytanie...",
      category: Category.PRZEPISY,
      firstAnswer: "Odpowiedź A",
      secondAnswer: "Odpowiedź B",
      thirdAnswer: "Odpowiedź C"
    },
    {
      id: 4,
      content: "Czwarte pytanie...",
      category: Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
      firstAnswer: "Odpowiedź A",
      secondAnswer: "Odpowiedź B",
      thirdAnswer: "Odpowiedź C"
    },
    {
      id: 5,
      content: "Piąte pytanie...",
      category: Category.PODSTAWOWE_PRZEPISY_PRAWA,
      firstAnswer: "Odpowiedź A",
      secondAnswer: "Odpowiedź B",
      thirdAnswer: "Odpowiedź C"
    },
    {
      id: 6,
      content: "Szóste pytanie...",
      category: Category.PRZEPISY,
      firstAnswer: "Odpowiedź A",
      secondAnswer: "Odpowiedź B",
      thirdAnswer: "Odpowiedź C"
    },
  ];

  consoleLoguj(): void {
    console.log("działa :D ");
  }

  selectQuestion(question: any): void {
    console.log(question);
  }

}
