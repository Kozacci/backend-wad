import {Component, Input, OnInit, Output} from '@angular/core';
import {CourseFilterDTO, CourseType} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";

export interface CourseDetails {
  type: string,
  title: string,
  image: string,
  profits: string[],
  conditions: string[],
  duration: string,
  yacht: string,
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

  checkAvailableCourses() {
    console.log("KURS PRZEKAZANY DO OGOLNEGO DETAILS : ", this.course)
    this.restClient.getCoursesByFilters(
      this.course.type,
      "NIEROZPOCZETY",
      null,
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
