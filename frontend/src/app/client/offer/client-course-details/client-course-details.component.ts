import {Component, Input, OnInit} from '@angular/core';
import {CourseFilterDTO} from "../../../shared/dto";
import {RestClient} from "../../../shared/rest-client";
import {AuthService} from "../../../shared/services/auth/auth.service";
import {MessageService} from "primeng/api";

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

  modalDateVisiblity: boolean = false;
  selectedAvailableCourseId: number | null = null;
  @Input()
  course: CourseDetails = <CourseDetails>{};
  availableCourses: CourseFilterDTO[] = <CourseFilterDTO[]>[];

  constructor(
    private readonly restClient: RestClient,
    public readonly authService: AuthService,
    private readonly messageService: MessageService,
  ) {}

  ngOnInit(): void {
    this.checkAvailableCourses()
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
          if(response == null) {
            this.availableCourses = [];
          }
        });
  }

  signInOnCourseModal() {
    if(this.authService.isLogged()) {
      this.modalDateVisiblity = true;
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

  selectAvailableCourse(id: number) {
    this.selectedAvailableCourseId = id;
  }

  signInOnCourse() {
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
              this.checkAvailableCourses();
              this.modalDateVisiblity = false;
              // Todo navigate to my courses view
            },
            error => {
            this.messageService.add({
              life: 4000,
              severity: 'error',
              summary: 'Zapis na kurs',
              detail: error.error.message
            });
          });
      }
  }

  // TODO form modal with placeholders AFTER ENGINEERING
  // this.formGroup.get('email')?.patchValue(sessionStorage.getItem('cacheEmail'));
  // this.formGroup.get('firstName')?.patchValue(sessionStorage.getItem('cacheFirstName'));
  // this.formGroup.get('lastName')?.patchValue(sessionStorage.getItem('cacheLastName'));
  // this.formGroup.get('phoneNumber')?.patchValue(sessionStorage.getItem('cachePhoneNumber'));
}
