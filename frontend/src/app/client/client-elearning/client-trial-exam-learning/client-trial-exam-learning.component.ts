import {Component} from '@angular/core';
import {ParticipantCourseEntityDTO, QuestionEntityDTO} from "../../../shared/dto";
import {RestClient} from "../../../shared/rest-client";
import {ActivatedRoute} from "@angular/router";
import {Subscription, takeWhile, timer} from "rxjs";
import {PathService} from "../../../shared/services/path.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-client-trial-exam-learning',
  templateUrl: './client-trial-exam-learning.component.html',
  styleUrls: ['./client-trial-exam-learning.component.css']
})
export class ClientTrialExamLearningComponent {

  participantCourseId: number = 0;
  participantCourse: ParticipantCourseEntityDTO = <ParticipantCourseEntityDTO>{};
  categories: string[] = [];
  randomQuestion: QuestionEntityDTO = <QuestionEntityDTO>{};
  randomQuestions: QuestionEntityDTO[] = [];
  randomQuestionIndex: number = 0;

  totalQuestions: number = 0;
  totalPassedQuestions: number = 0;
  percentage: number = 0;

  submitted: boolean = false;
  selectedAnswer: string | null = null;

  isFinished: boolean = false;
  initialTime = 60 * 60;
  timeLeft: number = this.initialTime;
  timerSubscription!: Subscription;

  protected readonly Math = Math;

  selectAnswer(answer: string) {
    this.selectedAnswer = answer;
    if(this.selectedAnswer == this.randomQuestion.correctAnswer) {
      this.totalPassedQuestions = this.totalPassedQuestions + 1;
    }
  }

  constructor(
    private readonly restClient: RestClient,
    private route: ActivatedRoute,
    private readonly pathService: PathService,
    private readonly messageService: MessageService
  ) {
  }
  ngOnInit() {
    this.getParticipantCourse();
    this.messageService.add({life:5000, severity:'info', summary:'Egzamin próbny', detail:"Rozpoczynasz egzamin próbny. Po udzieleniu wszystkich odpowiedzi kliknij przycisk do ukończenia egzaminu."})
    this.startTimer();
  }

  startTimer() {
    this.timerSubscription = timer(0, 1000).pipe(
      takeWhile(() => this.timeLeft > 0)
    ).subscribe(() => {
      this.timeLeft--;
      if (this.timeLeft === 0) {
        this.finishTrialExam(this.participantCourseId);
      }
    });
  }

  formatTime(seconds: number): string {
    const minutes: number = Math.floor(seconds / 60);
    const remainingSeconds: number = seconds % 60;
    const formattedMinutes: string = minutes.toString().padStart(2, '0');
    const formattedSeconds: string = remainingSeconds.toString().padStart(2, '0');
    return `${formattedMinutes}:${formattedSeconds}`;
  }

  getParticipantCourse() {
    this.route.params.subscribe(params => {
      this.participantCourseId = params['id'];
      this.restClient.getParticipantCourseById(this.participantCourseId)
        .subscribe(
          response => {
            this.participantCourse = response;
            this.getAllQuestionsAndDraw();
          }
        )
    });
  }

  getAllQuestionsAndDraw() {
    this.restClient.getAllQuestionsAndDraw()
      .subscribe(
        response => {
          this.randomQuestions = response;
          if(this.randomQuestions.at(this.randomQuestionIndex) != undefined) {
            this.randomQuestion = this.randomQuestions.at(this.randomQuestionIndex) as QuestionEntityDTO;
          }
          this.totalQuestions = this.randomQuestions.length;
        }
      )
  }

  getSelectedQuestion(index: number) {
    this.randomQuestionIndex = index;
    if(this.randomQuestions.at(this.randomQuestionIndex) != undefined) {
      this.randomQuestion = this.randomQuestions.at(this.randomQuestionIndex) as QuestionEntityDTO;
    }
    this.selectedAnswer = null;
    this.submitted = false;
  }

  getNextQuestion() {
    this.randomQuestionIndex = this.randomQuestionIndex + 1;
    if(this.randomQuestions.at(this.randomQuestionIndex) != undefined) {
      this.randomQuestion = this.randomQuestions.at(this.randomQuestionIndex) as QuestionEntityDTO;
    }
    this.selectedAnswer = null;
    this.submitted = false;
  }

  getPreviousQuestion() {
    this.randomQuestionIndex = this.randomQuestionIndex - 1;
    if(this.randomQuestions.at(this.randomQuestionIndex) != undefined) {
      this.randomQuestion = this.randomQuestions.at(this.randomQuestionIndex) as QuestionEntityDTO;
    }
    this.selectedAnswer = null;
    this.submitted = false;
  }

  finishTrialExam(participantCourseId: number) {
    // TODO -- check user's answer only once per question (actually it is incrementing after each click on selected block of answer)
    //      -- add how many questions have user answered on total
    //      -- restyle it a bit and make it responsive
    this.percentage = Math.round((this.totalPassedQuestions / this.totalQuestions) * 100);
    if(this.percentage > 70) {
      return this.restClient.updateTrialExamLearning(participantCourseId, true)
        .subscribe(
          response => {
            this.isFinished = true;
            if(this.timerSubscription) {
              this.timerSubscription.unsubscribe();
            }
          }
        )
    }
    else {
      return this.restClient.updateTrialExamLearning(participantCourseId, false)
        .subscribe(
          response => {
            this.isFinished = true;
            if(this.timerSubscription) {
              this.timerSubscription.unsubscribe();
            }
          }
        )
    }
  }

  goToStatistics() {
    const id = this.participantCourseId;
    this.pathService.navigate(`e-learning/statystyki/${id}`)
  }

  retryExam() {
    window.location.reload();
  }

}
