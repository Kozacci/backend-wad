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
  ClientEventDetailsBusinessComponent
} from './offer/client-event-details/client-event-details-business/client-event-details-business.component';
import {
  ClientEventDetailsSightseeingComponent
} from './offer/client-event-details/client-event-details-sightseeing/client-event-details-sightseeing.component';
import {
  ClientEventDetailsScooterComponent
} from './offer/client-event-details/client-event-details-scooter/client-event-details-scooter.component';
import {ClientMyProfileComponent} from './client-my-profile/client-my-profile.component';
import {ClientContactComponent} from './client-about/client-contact/client-contact.component';
import {ClientFaqComponent} from './client-about/client-faq/client-faq.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {ClientMyEventsComponent} from './client-my-events/client-my-events.component';
import {ClientMyCoursesComponent} from './client-my-courses/client-my-courses.component';
import {ClientElearningComponent} from './client-elearning/client-elearning.component';
import {ButtonModule} from "primeng/button";
import {TooltipModule} from "primeng/tooltip";
import {
  ClientGeneralLearningComponent
} from './client-elearning/client-general-learning/client-general-learning.component';
import {MatCardModule} from "@angular/material/card";
import {MatDividerModule} from "@angular/material/divider";
import { ClientCategoryLearningComponent } from './client-elearning/client-category-learning/client-category-learning.component';

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
    ClientEventDetailsBusinessComponent,
    ClientEventDetailsSightseeingComponent,
    ClientEventDetailsScooterComponent,
    ClientMyProfileComponent,
    ClientContactComponent,
    ClientFaqComponent,
    ClientMyEventsComponent,
    ClientMyCoursesComponent,
    ClientElearningComponent,
    ClientGeneralLearningComponent,
    ClientCategoryLearningComponent
  ],
  exports: [
    ClientLayoutComponent,
  ],
  imports: [
    SharedModule,
    CommonModule,
    NgOptimizedImage,
    ButtonModule,
    TooltipModule,
    AnimateModule,
    MatMenuModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    CarouselModule,
    RatingModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatExpansionModule,
  ]
})
export class ClientModule { }
