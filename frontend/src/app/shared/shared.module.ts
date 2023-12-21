import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";
import {TextIconComponent} from './text-icon/text-icon.component';
import {SidebarModule} from "primeng/sidebar";
import {DividerModule} from "primeng/divider";
import {PrimaryButtonComponent} from './primary-button/primary-button.component';
import {SecondaryButtonComponent} from './secondary-button/secondary-button.component';
import {ToastModule} from "primeng/toast";
import {MenubarModule} from "primeng/menubar";
import {HttpClientModule} from "@angular/common/http";
import {TableModule} from "primeng/table";
import {StyleClassModule} from "primeng/styleclass";
import {RippleModule} from "primeng/ripple";
import {DialogModule} from "primeng/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";
import { CorrectAnswerPipe } from './pipes/correct-answer.pipe';
import { CategoryPipe } from './pipes/category.pipe';
import {DangerButtonComponent} from "./danger-button/danger-button.component";
import {CalendarModule} from "primeng/calendar";
import { CourseTypePipe } from './pipes/course-type.pipe';
import { CourseStatusPipe } from './pipes/course-status.pipe';
import { CourseCityPipe } from './pipes/course-city.pipe';
import { EventCityPipe } from './pipes/event-city.pipe';
import { EventTypePipe } from './pipes/event-type.pipe';


@NgModule({
  declarations: [
    TextIconComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    DangerButtonComponent,
    CorrectAnswerPipe,
    CategoryPipe,
    CourseTypePipe,
    CourseStatusPipe,
    CourseCityPipe,
    EventCityPipe,
    EventTypePipe,
  ],
  imports: [
    CommonModule,
    ButtonModule,
    SidebarModule,
  ],
  exports: [
    ButtonModule,
    SidebarModule,
    DividerModule,
    TextIconComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    DangerButtonComponent,
    CommonModule,
    ButtonModule,
    SidebarModule,
    ToastModule,
    MenubarModule,
    HttpClientModule,
    TableModule,
    StyleClassModule,
    RippleModule,
    DialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    CorrectAnswerPipe,
    CategoryPipe,
    CalendarModule,
    CourseTypePipe,
    CourseStatusPipe,
    CourseCityPipe,
    EventCityPipe,
    EventTypePipe,
  ]
})
export class SharedModule { }
