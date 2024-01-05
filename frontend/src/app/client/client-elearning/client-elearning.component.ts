import {Component, OnInit} from '@angular/core';
import {ParticipantCourseFilterDTO} from "../../shared/dto";
import {MessageService} from "primeng/api";
import {ClientMyCoursesService} from "../client-my-courses/client-my-courses.service";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-elearning',
  templateUrl: './client-elearning.component.html',
  styleUrls: ['./client-elearning.component.css']
})
export class ClientElearningComponent implements OnInit {

  courses: ParticipantCourseFilterDTO[] = [];

  constructor(
    private readonly pathService: PathService,
    private readonly messageService: MessageService,
    protected readonly clientMyCoursesService: ClientMyCoursesService
  ) {}

  ngOnInit() {
    this.welcomeMessage();
    this.getParticipantCourses();
  }

  welcomeMessage() {
    // TODO - instead of warn message print on unpaid course that it is need to be paid to use e-learning
    const welcomeMessageDisplayed = sessionStorage.getItem('welcomeMessageDisplayed');
    if(!welcomeMessageDisplayed) {
      this.messageService.add({life:3800, severity:'info', summary:'E-Learning', detail:"Wybierz najpierw kurs i rodzaj nauki, z której chcesz skorzystać"})
      this.messageService.add({life:4500, severity:'warn', summary:'E-Learning', detail:"Pamiętaj, że tylko opłacone kursy mają dostęp do nauki"})
    }
    sessionStorage.setItem('welcomeMessageDisplayed', 'true');
  }

  // TODO -- DO NOT USE WARSZTATY_NAWIGACYJNE AND REJSY_STAZOWE ON THIS VIEW
  getParticipantCourses() {
    this.clientMyCoursesService.getParticipantCourses()
      .subscribe(response => {
        if(response != null) {
          this.courses = response;
        }
        else {
          this.courses = [];
        }
      })
  }

  goToStatistics(course: ParticipantCourseFilterDTO) {
    const id = course.participantCourseId;
    this.pathService.navigate(`e-learning/statystyki/${id}`)
  }

  goToGeneralLearning(course: ParticipantCourseFilterDTO) {
    const id = course.participantCourseId;
    this.pathService.navigate(`/e-learning/nauka-ogolna/${id}`)
  }

  goToCategoryLearning(course: ParticipantCourseFilterDTO) {
    const id = course.participantCourseId;
    this.pathService.navigate(`e-learning/nauka-dzialami/${id}`)
  }

  goToTrialExam(course: ParticipantCourseFilterDTO) {
    const id = course.participantCourseId;
    this.pathService.navigate(`e-learning/egzamin-probny/${id}`)
  }

  // TODO przekazac do url id participant course, sprawdzac czy ten kurs participant jakiego typu jest i zaleznie od tego generowac pytania pod to/działy/egzamin
  // TODO NASTEPNIE STATYSTYKI NAUKI BAZUJAC NA TYM SAMYM ID


}
