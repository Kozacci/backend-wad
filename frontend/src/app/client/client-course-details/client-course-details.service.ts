import { Injectable } from '@angular/core';
import {CourseDetails} from "./client-course-details.component";

@Injectable({
  providedIn: 'root'
})
export class ClientCourseDetailsService {

  ustawDaneDomyslne(course: CourseDetails,
                    type: string, title:string,
                    image:string, profits: string[],
                    conditions: string[], duration: string,
                    yacht: string, price: string) {
    course.type = type;
    course.title = title;
    course.image = image;
    course.profits = profits;
    course.conditions = conditions;
    course.duration = duration;
    course.yacht = yacht;
    course.price = price;
  }

}
