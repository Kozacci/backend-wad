import {Component} from '@angular/core';
import {GroupedErrorDTO, QuestionCreateUpdateDTO, QuestionFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";
import {FormService} from "../../shared/services/form/form.service";


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
  addQuestionModalVisible: boolean = false;
  editQuestionModalVisible: boolean = false;
  categoryToAdd: {name: string, value: string} | null = null;
  correctAnswerToAdd: {name: string, value: string} | null = null;
  questionsList: QuestionFilterDTO[] = [];

  formGroup = new FormGroup({
    categoryToAdd: new FormControl(this.questionToAdd.category,
      [Validators.required]),
    contentToAdd: new FormControl(this.questionToAdd.content,
      [Validators.required]),
    correctAnswer: new FormControl(this.questionToAdd.correctAnswer,
      [Validators.required]),
    firstAnswerToAdd: new FormControl(this.questionToAdd.firstAnswer,
      [Validators.required]),
    secondAnswerToAdd: new FormControl(this.questionToAdd.secondAnswer,
      [Validators.required]),
    thirdAnswerToAdd: new FormControl(this.questionToAdd.thirdAnswer,
      [Validators.required]),
  })

  groupedErrors: GroupedErrorDTO[] = [];

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService,
              protected formService: FormService) {
  }

  editQuestion(question: any): void {
    console.log(question);
  }

  findQuestions(): void {
    this.restClient.getQuestionsByFilters(this.id, this.content, this.category?.name, this.sortBy?.value)
      .subscribe((val) => {
        console.log(val)
        this.questionsList = val;
      });
  }

  addQuestion(): void {
    this.questionToAdd.category = <string>this.categoryToAdd?.value;
    this.questionToAdd.correctAnswer = <string>this.correctAnswerToAdd?.name;
    this.questionToAdd.content = <string>this.formGroup.value.contentToAdd;
    this.questionToAdd.firstAnswer = <string>this.formGroup.value.firstAnswerToAdd;
    this.questionToAdd.secondAnswer = <string>this.formGroup.value.secondAnswerToAdd;
    this.questionToAdd.thirdAnswer = <string>this.formGroup.value.thirdAnswerToAdd;
    this.restClient.addQuestion(this.questionToAdd).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Dodanie pytania", "Pytanie numer: " + response.questionId +  " zostało dodane.");
    }, error => {
      this.groupedErrors = this.responseHandlerService.getErrorsBelowInputs(error)
    });
  }

  showAddQuestionModal(): void {
    this.addQuestionModalVisible = true;
  }

  closeAddQuestionModal(): void {
    this.addQuestionModalVisible = false;
  }

  showEditQuestionModal(): void {
    this.editQuestionModalVisible = true;
  }

  closeEditQuestionModal(): void {
    this.editQuestionModalVisible = false;
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

  correctAnswerValues = [
    {name: "Odpowiedź A", value: "FIRST_ANSWER"},
    {name: "Odpowiedź B", value: "SECOND_ANSWER"},
    {name: "Odpowiedź C", value: "THIRD_ANSWER"},
  ]

}
