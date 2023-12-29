import {Component} from '@angular/core';
import {CourseType, ParticipantCourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";
import {MessageService} from "primeng/api";
import {ClientMyCoursesService} from "./client-my-courses.service";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-client-my-courses',
  templateUrl: './client-my-courses.component.html',
  styleUrls: ['./client-my-courses.component.css']
})
export class ClientMyCoursesComponent {

  courses: ParticipantCourseFilterDTO[] = [];

  constructor(
    private readonly restClient: RestClient,
    private readonly messageService: MessageService,
    protected readonly clientMyCoursesService: ClientMyCoursesService
  ) {}

  ngOnInit() {
    this.getParticipantCourses();
  }

  getParticipantCourses() {
    this.clientMyCoursesService.getParticipantCourses()
      .subscribe(response => {
        if(response != null) {
          this.courses = response;
        }
        else {
          this.courses = [];
          console.log(response);
        }
      })
  }

  cancelParticipantCourse(course: ParticipantCourseFilterDTO) {
    this.restClient.deleteAssigningForCourse(course.participantCourseId)
      .pipe(
        switchMap(async () => this.getParticipantCourses())
      )
      .subscribe(
        () => {
          this.messageService.add({life:5000, severity:'success', summary:'Rezygnacja z kursu', detail:"Pomyślnie udało Ci się zrezygnować z kursu"})
        },
        (error) => {
          this.messageService.add({life:4000, severity:'error', summary:'Rezygnacja z kursu', detail: error.error.message})
          console.error('Błąd rezygnacji z kursu', error);
        })
  }

}
