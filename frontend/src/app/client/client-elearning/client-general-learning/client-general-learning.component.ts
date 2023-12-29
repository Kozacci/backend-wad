import {Component, OnInit} from '@angular/core';
import {RestClient} from "../../../shared/rest-client";
import {ActivatedRoute} from "@angular/router";
import {Category, ParticipantCourseEntityDTO, QuestionEntityDTO} from "../../../shared/dto";
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

  constructor(
    private readonly restClient: RestClient,
    private route: ActivatedRoute,
    private readonly clientElearningService: ClientElearningService
  ) {
  }
  ngOnInit() {
    this.getParticipantCourse();
    // this.getRandomQuestion(); --  wywolywac na starcie w metodzie do pobrania kursu uzytkownika a pozniej przy kliknieciu w nastepne pytanie...
  }

  getParticipantCourse() {
    this.route.params.subscribe(params => {
      this.participantCourseId = this.route.snapshot.params['id'];
      this.restClient.getParticipantCourseById(this.participantCourseId).subscribe(
        response => {
          this.participantCourse = response;
          this.categories = this.clientElearningService.getCategoriesByCourseType(this.participantCourse);
          this.getRandomQuestion();
        }
      )
    });
  }

  getRandomQuestion() {
    this.restClient.getRandomQuestionByCategories(this.categories).subscribe(
      response => {
        this.randomQuestion = response;
        console.log("LOSOWE PYTANIE:", this.randomQuestion);
      }
    )
  }

}
