import {Injectable} from '@angular/core';
import {CourseDetails} from "./client-course-details.component";
import {EventDetails} from "../client-event-details/client-event-details.component";

@Injectable({
  providedIn: 'root'
})
export class ClientCourseDetailsService {

  ustawDaneDomyslne(course: CourseDetails | EventDetails,
                    type: string, title:string,
                    image:string, profits: string[],
                    conditions: string[], duration: string,
                    yacht: string, additionalInfo1: string,
                    additionalInfo2: string, additionalInfo3: string,
                    additionalInfo4: string, price: string) {
    course.type = type;
    course.title = title;
    course.image = image;
    course.profits = profits;
    course.conditions = conditions;
    course.duration = duration;
    course.yacht = yacht;
    course.additionalInfo1 = additionalInfo1;
    course.additionalInfo2 = additionalInfo2;
    course.additionalInfo3 = additionalInfo3;
    course.additionalInfo4 = additionalInfo4;
    course.price = price;
  }

}
