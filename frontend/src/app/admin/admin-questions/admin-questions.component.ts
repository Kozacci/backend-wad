import {Component} from '@angular/core';
import {
  Category,
  CorrectAnswer,
  GroupedErrorDTO,
  mapToCategory,
  mapToCorrectAnswer,
  QuestionCreateUpdateDTO,
  QuestionEntityDTO,
  QuestionFilterDTO
} from "../../shared/dto";
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
  questionToEdit: QuestionCreateUpdateDTO = <QuestionCreateUpdateDTO><unknown>{
    category: null, content: null, correctAnswer: null,
    firstAnswer: null, thirdAnswer: null, secondAnswer: null
  }
  questionToEditId: number | null = null;
  addQuestionModalVisible: boolean = false;
  editQuestionModalVisible: boolean = false;
  categoryToAdd: {name: string, value: string} | null = null;
  correctAnswerToAdd: {name: string, value: string} | null = null;
  questionsList: QuestionFilterDTO[] = [];
  categoryToEdit: {name: string, value: string} | undefined = undefined;
  correctAnswerToEdit: {name: string, value: string} | undefined = undefined;

  formGroupAddQuestion = new FormGroup({
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
  });

  formGroupEditQuestion = new FormGroup({
    categoryToEdit: new FormControl(this.questionToEdit.category,
      [Validators.required]),
    contentToEdit: new FormControl(this.questionToEdit.content,
      [Validators.required]),
    correctAnswer: new FormControl(this.questionToEdit.correctAnswer,
      [Validators.required]),
    firstAnswerToAdd: new FormControl(this.questionToEdit.firstAnswer,
      [Validators.required]),
    secondAnswerToAdd: new FormControl(this.questionToEdit.secondAnswer,
      [Validators.required]),
    thirdAnswerToAdd: new FormControl(this.questionToEdit.thirdAnswer,
      [Validators.required]),
  });

  groupedErrors: GroupedErrorDTO[] = [];

  constructor(private restClient: RestClient,
              protected responseHandlerService: HttpResponseHandlerService,
              protected formService: FormService) {
  }

  findQuestions(): void {
    this.restClient.getQuestionsByFilters(this.id, this.content, this.category?.name, this.sortBy?.value)
      .subscribe((val) => {
        this.questionsList = val;
      });
  }

  addQuestion(): void {
    this.questionToAdd.category = <Category>this.categoryToAdd?.value;
    this.questionToAdd.correctAnswer = <CorrectAnswer>this.correctAnswerToAdd?.name;
    this.questionToAdd.content = <string>this.formGroupAddQuestion.value.contentToAdd;
    this.questionToAdd.firstAnswer = <string>this.formGroupAddQuestion.value.firstAnswerToAdd;
    this.questionToAdd.secondAnswer = <string>this.formGroupAddQuestion.value.secondAnswerToAdd;
    this.questionToAdd.thirdAnswer = <string>this.formGroupAddQuestion.value.thirdAnswerToAdd;
    this.restClient.addQuestion(this.questionToAdd).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Dodanie pytania", "Pytanie numer: " + response.questionId +  " zostało dodane.");
      this.appendAddedQuestionToTable(response);
    }, error => {
      this.groupedErrors = this.responseHandlerService.getErrorsBelowInputs(error)
    });
  }

  private appendAddedQuestionToTable(response: QuestionEntityDTO) {
    this.questionsList = this.questionsList.concat([<QuestionFilterDTO>{
      id: response.questionId,
      content: response.content,
      category: mapToCategory(response.category),
      firstAnswer: response.firstAnswer,
      secondAnswer: response.secondAnswer,
      thirdAnswer: response.thirdAnswer,
      correctAnswer: mapToCorrectAnswer(response.correctAnswer)
    }
    ]);
  }

  insertDataIntoEditQuestionModal(question: QuestionFilterDTO): void {
    this.categoryToEdit = this.categories.find(cat => cat.name == question.category.toString());
    this.correctAnswerToEdit = this.correctAnswerValues.find(ca => ca.value == question.correctAnswer.toString());
    this.questionToEditId = question.id;
    this.formGroupEditQuestion.controls.contentToEdit.setValue(question.content);
    this.formGroupEditQuestion.controls.categoryToEdit.setValue(question.category);
    this.formGroupEditQuestion.controls.firstAnswerToAdd.setValue(question.firstAnswer);
    this.formGroupEditQuestion.controls.secondAnswerToAdd.setValue(question.secondAnswer);
    this.formGroupEditQuestion.controls.thirdAnswerToAdd.setValue(question.thirdAnswer);
    this.formGroupEditQuestion.controls.correctAnswer.setValue(question.correctAnswer);
    this.showEditQuestionModal();
    // this.formGroupEditQuestion.reset();
  }

  editQuestion(): void {
    this.questionToEdit.category = <Category>this.categoryToEdit?.value;
    this.questionToEdit.correctAnswer = <CorrectAnswer>this.correctAnswerToEdit?.name;
    this.questionToEdit.content = <string>this.formGroupEditQuestion.value.contentToEdit;
    this.questionToEdit.firstAnswer = <string>this.formGroupEditQuestion.value.firstAnswerToAdd;
    this.questionToEdit.secondAnswer = <string>this.formGroupEditQuestion.value.secondAnswerToAdd;
    this.questionToEdit.thirdAnswer = <string>this.formGroupEditQuestion.value.thirdAnswerToAdd;
    console.log("this.questionToEdit= ", this.questionToEdit)
    this.restClient.editQuestion(this.questionToEdit, this.questionToEditId!).subscribe(response => {
      this.responseHandlerService.showSuccessPToast("Edycja pytania", "Pytanie numer: " + response.questionId +  " zostało zedytowane.");
      this.changeEditedQuestionValuesInTable();
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    });
  }

  private changeEditedQuestionValuesInTable() {
    let editedQuestion = this.questionsList.find(editedQuestion => editedQuestion.id == this.questionToEditId)!;
    editedQuestion.content = this.questionToEdit.content;
    editedQuestion.category = mapToCategory(this.questionToEdit.category);
    editedQuestion.correctAnswer = mapToCorrectAnswer(this.questionToEdit.correctAnswer);
    editedQuestion.firstAnswer = this.questionToEdit.firstAnswer;
    editedQuestion.secondAnswer = this.questionToEdit.secondAnswer;
    editedQuestion.thirdAnswer = this.questionToEdit.thirdAnswer;
  }

  deleteQuestion(): void {
    this.restClient.deleteQuestionById(this.questionToEditId).subscribe( () => {
      this.responseHandlerService.showSuccessPToast("Usunięcie pytania", "Pytanie nr:" + this.questionToEditId + " zostało usunięte.");
      this.removeQuestionFromTable(this.questionToEditId);
      this.closeEditQuestionModal();
    }, error => {
      this.responseHandlerService.handleErrorsPtoasts(error);
    })
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

  removeQuestionFromTable(deletedQuestionId: number | null) {
    if (deletedQuestionId == null) {
      return;
    }
    this.questionsList = this.questionsList.filter(questions => questions.id !== deletedQuestionId);
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
