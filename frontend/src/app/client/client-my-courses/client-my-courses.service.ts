import {Injectable} from '@angular/core';
import {CourseType, ParticipantCourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class ClientMyCoursesService {
  constructor(
    private readonly restClient: RestClient,
    private readonly pathService: PathService,
    private readonly messageService: MessageService
  ) {}

  getParticipantCourses() {
    const participantId = Number(sessionStorage.getItem('cacheId'));
    return this.restClient
      .getCoursesByParticipantIdAndFilters(participantId, undefined, undefined,
        null, null, 'dateFrom',
        null, null, null, undefined, null)
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

  goToCourseLearning() {
    this.pathService.navigate('/e-learning')
  }

}
