import {Component} from '@angular/core';
import {CourseType, ParticipantCourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-client-my-courses',
  templateUrl: './client-my-courses.component.html',
  styleUrls: ['./client-my-courses.component.css']
})
export class ClientMyCoursesComponent {

  courses: ParticipantCourseFilterDTO[] = [];

  constructor(private readonly restClient: RestClient,
              private readonly pathService: PathService,
              private readonly messageService: MessageService
  ) { }

  ngOnInit() {
    this.getParticipantCourses();
  }

  getParticipantCourses() {
    const participantId = Number(sessionStorage.getItem('cacheId'));
    return this.restClient
      .getCoursesByParticipantIdAndFilters(participantId, undefined, undefined,
        null, null, 'dateFrom',
        null, null, null, undefined)
      .subscribe(response => {
        if(response != null) {
          this.courses = response;
          console.log(response);
        }
      })
  }

  getImage(course: ParticipantCourseFilterDTO): string {
    switch (course.courseType) {
      case CourseType.STERNIK_MOTOROWODNY:
        return 'assets/images/client/overlay/courses/course-1.png';
      case CourseType.JACHTOWY_STERNIK_MORSKI:
        return 'assets/images/client/overlay/courses/course-3.png';
      case CourseType.MOTOROWODNY_STERNIK_MORSKI:
        return 'assets/images/client/overlay/courses/course-4.png';
      case CourseType.ZEGLARZ_JACHTOWY:
        return 'assets/images/client/overlay/courses/course-5.png';
      case CourseType.WARSZTATY_NAWIGACYJNE:
        return 'assets/images/client/overlay/courses/course-6.png';
      default:
        return 'assets/images/client/overlay/courses/course-7.png';
    }
  }

  goToCourseDetails(course: ParticipantCourseFilterDTO) {
    switch (course.courseType) {
      case CourseType.STERNIK_MOTOROWODNY:
        return this.pathService.navigate('/oferta/kursy/sternik-motorowodny')
      case CourseType.JACHTOWY_STERNIK_MORSKI:
        return this.pathService.navigate('/oferta/kursy/jachtowy-sternik')
      case CourseType.MOTOROWODNY_STERNIK_MORSKI:
        return this.pathService.navigate('/oferta/kursy/morski-sternik')
      case CourseType.ZEGLARZ_JACHTOWY:
        return this.pathService.navigate('/oferta/kursy/zeglarz-jachtowy')
      case CourseType.WARSZTATY_NAWIGACYJNE:
        return this.pathService.navigate('/oferta/kursy/warsztaty-nawigacyjne')
      default:
        return this.pathService.navigate('/oferta/kursy/rejsy-stazowe')
    }
  }

  goToCourseLearning(course: ParticipantCourseFilterDTO) {
    // TODO
  }

  cancelParticipantCourse(course: ParticipantCourseFilterDTO) {
    this.restClient.deleteAssigningForCourse(course.participantCourseId)
      .subscribe(
        () => {
          this.getParticipantCourses();
          this.messageService.add({life:5000, severity:'success', summary:'Rezygnacja z kursu', detail:"Pomyślnie udało Ci się zrezygnować z kursu"})
        },
        (error) => {
          this.messageService.add({life:4000, severity:'error', summary:'Rezygnacja z kursu', detail: error.error.message})
          console.error('Błąd rezygnacji z kursu', error);
        })
  }

}
