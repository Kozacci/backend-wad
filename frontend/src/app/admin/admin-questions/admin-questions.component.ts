import {Component} from '@angular/core';
import {Category, ParticipantLoginDTO, QuestionFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";

@Component({
  selector: 'app-admin-questions',
  templateUrl: './admin-questions.component.html',
  styleUrls: ['./admin-questions.component.css']
})
export class AdminQuestionsComponent {

  id!: number;
  content!: string;
  category!: Category;
  sortBy!: string;

  constructor(private restClient: RestClient) {
  }

  selectQuestion(question: any): void {
    console.log(question);
  }

  findQuestions(): void {
    this.restClient.getQuestionsByFilters(this.id, this.content, this.category, this.sortBy)
      .subscribe((val) => {
        console.log("consoleLoguje:" + val)
        this.questions = val;
      });
  }

  logowanieDoWywalenia(): void {
    let parti : ParticipantLoginDTO = <ParticipantLoginDTO> {
      email: 'admin@email.com',
      password: 'admin123'
    }
    this.restClient.login(parti).subscribe((response) => console.log(response));
  }

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

}
