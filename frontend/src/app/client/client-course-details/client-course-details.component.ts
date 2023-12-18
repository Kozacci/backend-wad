import {Component, Input, OnInit, Output} from '@angular/core';
import {CourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {AuthService} from "../../shared/services/auth/auth.service";
import {MessageService} from "primeng/api";
import {HttpResponseHandlerService} from "../../shared/services/http-response-handler.service";

export interface CourseDetails {
  type: string,
  title: string,
  image: string,
  profits: string[],
  conditions: string[],
  duration: string,
  yacht: string,
  additionalInfo1: string,
  additionalInfo2: string,
  additionalInfo3: string,
  additionalInfo4: string,
  price: string,
  availableCourses: CourseFilterDTO[];
}

@Component({
  selector: 'app-client-course-details',
  templateUrl: './client-course-details.component.html',
  styleUrls: ['./client-course-details.component.css']
})
export class ClientCourseDetailsComponent implements OnInit {

  modalVisiblity: boolean = false;
  selectedAvailableCourseId: number | null = null;
  @Input()
  course: CourseDetails = <CourseDetails>{};
  @Output()
  availableCourses: CourseFilterDTO[] = <CourseFilterDTO[]>{};

  constructor(
    private readonly restClient: RestClient,
    public readonly authService: AuthService,
    private readonly messageService: MessageService,
    private readonly httpResponseHandlerService: HttpResponseHandlerService
  ) {}

  ngOnInit(): void {
    this.checkAvailableCourses()
  }

  signInOnCourseModal() {
    if(this.authService.isLogged()) {
      this.modalVisiblity = true;
    }
    else {
      this.messageService.add({
        life:5000,
        severity:'error',
        summary:'Logowanie',
        detail:"Musisz się najpierw zalogować!"}
      )
    }
  }

  signInOnCourseModalClose() {
    this.modalVisiblity = false;
  }

  selectAvailableCourse(id: number) {
    this.selectedAvailableCourseId = id;
  }

  // Todo - form and placeholder taken from getParticipantByEmail(cachEmail) rest method
  // Todo - everything same for events
  // Todo - e-learning from beginning
  singInOnCourse() {
    const participantId = parseInt(sessionStorage.getItem('cacheId')?? '', 10) ;
    if (this.selectedAvailableCourseId != null) {
      this.restClient.signInOnCourse(participantId, this.selectedAvailableCourseId)
        .subscribe( response => {
            this.messageService.add({
              life: 4000,
              severity: 'success',
              summary: 'Zapis na kurs',
              detail: "Udało Ci się pomyślnie zapisać na kurs"
            })
            this.modalVisiblity = false;
          },
          error => {
            this.messageService.add({
              life: 4000,
              severity: 'error',
              summary: 'Zapis na kurs',
              detail: error.error.message
            });
          }
        )
        ;
    }
  }

  // Todo - 403 error without loggin' in
  checkAvailableCourses() {
    this.restClient.getCoursesByFilters(
      this.course.type,
      "NIEROZPOCZETY",
      undefined,
      null,
      null,
      null,
      null,
      "dateFrom"
      )
      .subscribe(
        response => {
          this.availableCourses = response;
          this.course.availableCourses = response;
          console.log(response);
        });
  }
}
