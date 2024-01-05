import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {AdminNavbarComponent} from './admin-navbar/admin-navbar.component';
import {SharedModule} from "../shared/shared.module";
import {AdminLayoutComponent} from './admin-layout.component';
import {AdminQuestionsComponent} from './admin-questions/admin-questions.component';
import {AdminCoursesComponent} from './admin-courses/admin-courses.component';
import {AdminEventsComponent} from './admin-events/admin-events.component';
import {AdminPassingComponent} from './admin-passing/admin-passing.component';
import {AdminAccessesComponent} from './admin-acceses/admin-accesses.component';
import {AdminCalendarComponent} from './admin-calendar/admin-calendar.component';
import {FormsModule} from "@angular/forms";
import {DropdownModule} from "primeng/dropdown";

@NgModule({
  declarations: [
    AdminHomeComponent,
    AdminNavbarComponent,
    AdminLayoutComponent,
    AdminQuestionsComponent,
    AdminCoursesComponent,
    AdminEventsComponent,
    AdminPassingComponent,
    AdminAccessesComponent,
    AdminCalendarComponent,
  ],
  exports: [
    AdminLayoutComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    DropdownModule,
  ],
  providers: [
    DatePipe
  ]
})
export class AdminModule { }
