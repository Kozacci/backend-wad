import {Component, Input, OnInit, Output} from '@angular/core';
import {CourseFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";

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

  @Input()
  course: CourseDetails = <CourseDetails>{};
  @Output()
  availableCourses: CourseFilterDTO[] = <CourseFilterDTO[]>{};

  constructor(
    private readonly restClient: RestClient
  ) {}

  ngOnInit(): void {
    this.checkAvailableCourses()
  }

  // TODO - 403 error without loggin' in
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
