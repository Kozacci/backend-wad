import {Component} from '@angular/core';
import {ParticipantCourseFilterDTO} from "../../shared/dto";
import {MessageService} from "primeng/api";
import {ClientMyCoursesService} from "../client-my-courses/client-my-courses.service";

@Component({
  selector: 'app-client-elearning',
  templateUrl: './client-elearning.component.html',
  styleUrls: ['./client-elearning.component.css']
})
export class ClientElearningComponent {

  courses: ParticipantCourseFilterDTO[] = [];

  constructor(
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

  getParticipantCourses() {
    this.clientMyCoursesService.getParticipantCourses()
      .subscribe(response => {
        if(response != null) {
          this.courses = response;
        }
      })
  }

}
