import {Component, OnInit} from '@angular/core';
import {RestClient} from "../../../shared/rest-client";
import {ActivatedRoute} from "@angular/router";
import {ParticipantCourseEntityDTO, QuestionEntityDTO} from "../../../shared/dto";
import {ClientElearningService} from "../client-elearning.service";

@Component({
  selector: 'app-client-general-learning',
  templateUrl: './client-general-learning.component.html',
  styleUrls: ['./client-general-learning.component.css']
})
export class ClientGeneralLearningComponent implements OnInit {

  participantCourseId: number = 0;
  participantCourse: ParticipantCourseEntityDTO = <ParticipantCourseEntityDTO>{};
  categories: string[] = [];
  randomQuestion: QuestionEntityDTO = <QuestionEntityDTO>{};

  submitted: boolean = false;
  selectedAnswer: string | null = null;

  selectAnswer(answer: string) {
    this.selectedAnswer = answer;
  }

  checkAnswer() {
    if(this.selectedAnswer == this.randomQuestion.correctAnswer) {
      this.updateGeneralLearning(this.participantCourseId, true)
    }
    else {
      this.updateGeneralLearning(this.participantCourseId, false)
    }
    this.submitted = true;
  }

  constructor(
    private readonly restClient: RestClient,
    private route: ActivatedRoute,
    private readonly clientElearningService: ClientElearningService
  ) {
  }
  ngOnInit() {
    this.getParticipantCourse();
  }

  getParticipantCourse() {
    this.route.params.subscribe(params => {
      this.participantCourseId = params['id'];
      this.restClient.getParticipantCourseById(this.participantCourseId)
        .subscribe(
        response => {
          this.participantCourse = response;
          this.categories = this.clientElearningService.getCategoriesByCourseType(this.participantCourse);
          this.getRandomQuestion();
        }
      )
    });
  }

  getRandomQuestion() {
    this.restClient.getRandomQuestionByCategories(this.categories)
      .subscribe(
      response => {
        this.randomQuestion = response;
        console.log("LOSOWE PYTANIE:", this.randomQuestion);
        this.selectedAnswer = null;
        this.submitted = false;
      }
    )
  }

  updateGeneralLearning(participantCourseId: number, isCorrectAnswer: boolean) {
    return this.restClient.updateGeneralLearning(participantCourseId, isCorrectAnswer)
      .subscribe(
        response => {
          console.log("GENERAL LEARNING ENTITY DTO : ", response);
        }
      )
  }

}
