import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ClientLayoutComponent} from './client-layout.component';
import {ClientHomeComponent} from './client-home/client-home.component';
import {ClientNavbarComponent} from './client-navbar/client-navbar.component';
import {SharedModule} from "../shared/shared.module";
import {AnimateModule} from "primeng/animate";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {CarouselModule} from "primeng/carousel";
import {RatingModule} from "primeng/rating";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ClientFooterComponent} from './client-footer/client-footer.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {ClientCoursesComponent} from './offer/client-courses/client-courses.component';
import {ClientCourseDetailsComponent} from './offer/client-course-details/client-course-details.component';
import {
  ClientCourseDetailsSteersmanComponent
} from './offer/client-course-details/client-course-details-steersman/client-course-details-steersman.component';
import {
  ClientCourseDetailsYachtComponent
} from './offer/client-course-details/client-course-details-yacht/client-course-details-yacht.component';
import {
  ClientCourseDetailsSeaComponent
} from './offer/client-course-details/client-course-details-sea/client-course-details-sea.component';
import {
  ClientCourseDetailsSailorComponent
} from './offer/client-course-details/client-course-details-sailor/client-course-details-sailor.component';
import {
  ClientCourseDetailsWorkshopComponent
} from './offer/client-course-details/client-course-details-workshop/client-course-details-workshop.component';
import {
  ClientCourseDetailsInternshipComponent
} from './offer/client-course-details/client-course-details-internship/client-course-details-internship.component';
import {ClientEventsComponent} from './offer/client-events/client-events.component';
import {ClientEventDetailsComponent} from './offer/client-event-details/client-event-details.component';
import {
  ClientEventDetailsBacheloretteComponent
} from './offer/client-event-details/client-event-details-bachelorette/client-event-details-bachelorette.component';
import {
  ClientEventDetailsBachelorComponent
} from './offer/client-event-details/client-event-details-bachelor/client-event-details-bachelor.component';
import {
  ClientEventDetailsSunsetComponent
} from './offer/client-event-details/client-event-details-sunset/client-event-details-sunset.component';
import {
  ClientEventDetailsBusinessComponent
} from './offer/client-event-details/client-event-details-business/client-event-details-business.component';
import {
  ClientEventDetailsSightseeingComponent
} from './offer/client-event-details/client-event-details-sightseeing/client-event-details-sightseeing.component';
import {
  ClientEventDetailsScooterComponent
} from './offer/client-event-details/client-event-details-scooter/client-event-details-scooter.component';

@NgModule({
  declarations: [
    ClientLayoutComponent,
    ClientHomeComponent,
    ClientNavbarComponent,
    ClientFooterComponent,
    ClientCoursesComponent,
    ClientCourseDetailsComponent,
    ClientCourseDetailsSteersmanComponent,
    ClientCourseDetailsYachtComponent,
    ClientCourseDetailsSeaComponent,
    ClientCourseDetailsSailorComponent,
    ClientCourseDetailsWorkshopComponent,
    ClientCourseDetailsInternshipComponent,
    ClientEventsComponent,
    ClientEventDetailsComponent,
    ClientEventDetailsBacheloretteComponent,
    ClientEventDetailsBachelorComponent,
    ClientEventDetailsSunsetComponent,
    ClientEventDetailsBusinessComponent,
    ClientEventDetailsSightseeingComponent,
    ClientEventDetailsScooterComponent
  ],
  exports: [
    ClientLayoutComponent,
  ],
  imports: [
    SharedModule,
    CommonModule,
    NgOptimizedImage,
    AnimateModule,
    MatMenuModule,
    MatButtonModule,
    CarouselModule,
    RatingModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
  ]
})
export class ClientModule { }
