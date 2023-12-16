import {Component} from '@angular/core';
import {Category, CorrectAnswer, QuestionCreateUpdateDTO, QuestionFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-admin-questions',
  templateUrl: './admin-questions.component.html',
  styleUrls: ['./admin-questions.component.css']
})
export class AdminQuestionsComponent {

  id: number | null = null;
  content: string | null = null;
  category: {name: string, value: string} | null = null;
  sortBy: {name: string, value: string} | null = null;
  questionToAdd: QuestionCreateUpdateDTO = <QuestionCreateUpdateDTO><unknown>{
    category: null, content: null, correctAnswer: null,
    firstAnswer: null, thirdAnswer: null, secondAnswer: null
  };
  modalVisible: boolean = false;
  categoryToAdd: FormControl<Category | null>  = new FormControl(this.questionToAdd.category,
    [Validators.required]);
  contentToAdd= new FormControl(this.questionToAdd.content,
    [Validators.required]);
  correctAnswer: FormControl<CorrectAnswer | null> = new FormControl(this.questionToAdd.correctAnswer,
    [Validators.required]);
  firstAnswerToAdd = new FormControl(this.questionToAdd.firstAnswer,
    [Validators.required]);
  secondAnswerToAdd = new FormControl(this.questionToAdd.secondAnswer,
    [Validators.required]);
  thirdAnswerToAdd = new FormControl(this.questionToAdd.thirdAnswer,
    [Validators.required]);

  constructor(private restClient: RestClient) {
  }

  getInputErrorMessage(input: FormControl<any | null>) {
    if (input.hasError('required')) {
      return 'Musisz wypełnić pole!';
    }
    else if (input.hasError('minlength')) {
      const maxRequiredLength = input.getError('minlength').requiredLength;
      return 'Pole musi zawierać min. ' + maxRequiredLength + ' znaków!';
    }
    else if (input.hasError('maxlength')) {
      const minRequiredLength = input.getError('maxlength').requiredLength;
      return 'Pole musi zawierać max. ' + minRequiredLength + ' znaków!';
    }
    else {
      return 'Niepoprawne pole';
    }
  }

  selectQuestion(question: any): void {
    console.log(question);
  }

  findQuestions(): void {
    this.restClient.getQuestionsByFilters(this.id, this.content, this.category?.name, this.sortBy?.value)
      .subscribe((val) => {
        this.questions = val;
      });
  }

  addQuestion(): void {
    this.restClient.addQuestion(this.questionToAdd).subscribe(val => {
      console.log(val)
    });
  }

  showModal(): void {
    this.modalVisible = true;
  }

  closeModal(): void {
    this.modalVisible = false;
  }

  categories = [
    { name: "PRZEPISY", value: "Przepisy"},
    { name: "PODSTAWY_LOCJI", value: "Podstawy locji"},
    { name: "WIADOMOSCI_Z_METEOROLOGII", value: "Wiadomości z zakresu meteorologii"},
    { name: "PODSTAWY_BUDOWY_JACHTOW", value: "Podstawy budowy jachtów motorowodnych"},
    { name: "SILNIKI_I_UKLADY_NAPEDOWE", value: "Silniki i układy napędowe"},
    { name: "WIADOMOSCI_Z_RATOWNICTWA_WODNEGO", value: "Wiadomości z zakresu ratownictwa wodnego"},
    { name: "POMOCE_NAWIGACYJNE", value: "Pomoce nawigacyjne"},
    { name: "OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM", value: "Ochrona wód przed zanieczyszczeniem"},
    { name: "PODSTAWOWE_PRZEPISY_PRAWA", value: "Podstawowe przepisy prawa drogi na morskich i śródlądowych drogach wodnych"},
    { name: "TEORIA_ZEGLOWANIA", value: "Teoria żeglowania"}
  ];

  sortByValues = [
    { name: "Numer", value: "id"},
    { name: "Treść", value: "content"},
    { name: "Dział", value: "category"},
    { name: "Odpowiedź A", value: "firstAnswer"},
    { name: "Odpowiedź B", value: "secondAnswer"},
    { name: "Odpowiedź C", value: "thirdAnswer"},
  ]

  questions: QuestionFilterDTO[] = [
    // {
    //   id: 1,
    //   content: "Pierwsze pytanie...",
    //   category: Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 2,
    //   content: "Drugie pytanie...",
    //   category: Category.PODSTAWOWE_PRZEPISY_PRAWA,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 3,
    //   content: "Trzecie pytanie...",
    //   category: Category.PRZEPISY,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 4,
    //   content: "Czwarte pytanie...",
    //   category: Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 5,
    //   content: "Piąte pytanie...",
    //   category: Category.PODSTAWOWE_PRZEPISY_PRAWA,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 6,
    //   content: "Szóste pytanie...",
    //   category: Category.PRZEPISY,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 6,
    //   content: "Szóste pytanie...",
    //   category: Category.PRZEPISY,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 6,
    //   content: "Szóste pytanie...",
    //   category: Category.PRZEPISY,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 6,
    //   content: "Szóste pytanie...",
    //   category: Category.PRZEPISY,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
    // {
    //   id: 6,
    //   content: "Szóste pytanie...",
    //   category: Category.PRZEPISY,
    //   firstAnswer: "Odpowiedź A",
    //   secondAnswer: "Odpowiedź B",
    //   thirdAnswer: "Odpowiedź C"
    // },
  ];

}
