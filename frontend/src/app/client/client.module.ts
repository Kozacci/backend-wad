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
import {ClientCoursesComponent} from './client-courses/client-courses.component';
import { ClientCourseDetailsComponent } from './client-course-details/client-course-details.component';
import { ClientCourseDetailsSteersmanComponent } from './client-course-details/client-course-details-steersman/client-course-details-steersman.component';
import { ClientCourseDetailsYachtComponent } from './client-course-details/client-course-details-yacht/client-course-details-yacht.component';
import { ClientCourseDetailsSeaComponent } from './client-course-details/client-course-details-sea/client-course-details-sea.component';
import { ClientCourseDetailsSailorComponent } from './client-course-details/client-course-details-sailor/client-course-details-sailor.component';
import { ClientCourseDetailsWorkshopComponent } from './client-course-details/client-course-details-workshop/client-course-details-workshop.component';
import { ClientCourseDetailsInternshipComponent } from './client-course-details/client-course-details-internship/client-course-details-internship.component';
import { ClientEventsComponent } from './client-events/client-events.component';

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
    ClientEventsComponent
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
