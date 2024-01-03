import {Component, OnInit} from '@angular/core';
import {EntireLearningDTO} from "../../../shared/dto";
import {RestClient} from "../../../shared/rest-client";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-client-statistics-learning',
  templateUrl: './client-statistics-learning.component.html',
  styleUrls: ['./client-statistics-learning.component.css']
})
export class ClientStatisticsLearningComponent implements OnInit {

  participantCourseId: number = 0;
  entireLearningDTO: EntireLearningDTO = <EntireLearningDTO>{};
  lineChartData: any;
  pieChartData: any;
  options: any;

  constructor(
    private readonly restClient: RestClient,
    private route: ActivatedRoute
  ) { }

  getEntireLearningByParticipantId() {
    this.route.params.subscribe(params => {
      this.participantCourseId = params['id'];
      this.restClient.getAnswerHistoryByParticipantCourseId(this.participantCourseId)
        .subscribe(
          response => {
            console.log(response);
            this.entireLearningDTO = response;
            this.lineChartData = {
              labels: ['Łącznie'],
              datasets: [
                {
                  label: 'Poprawne odpowiedzi',
                  data: [this.entireLearningDTO.generalLearningEntityDTO.correctAnswers],
                  backgroundColor: '#39A2DB'
                },
                {
                  label: 'Wszystkie odpowiedzi',
                  data: [this.entireLearningDTO.generalLearningEntityDTO.questionsAnswered],
                  backgroundColor: '#053742'
                }
              ]

            };
            this.pieChartData = {
              labels: ['Egzaminy zaliczone', 'Egzaminy niezaliczone'],
              datasets: [
                {
                  data: [this.entireLearningDTO.trialExamEntityDTO.passed, this.entireLearningDTO.trialExamEntityDTO.failed],
                  backgroundColor: [
                    "#39A2DB",
                    "#053742"
                  ]
                }
              ]
            };
            this.options = {
              scales: {
                y: {
                  beginAtZero: true
                }
              },
              plugins: {
                legend: {
                  display: true
                }
              }
            };
          }
        )
    });
  }

  ngOnInit() {
    this.getEntireLearningByParticipantId()
  }

}
