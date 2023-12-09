import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {AdminNavbarComponent} from './admin-navbar/admin-navbar.component';
import {SharedModule} from "../shared/shared.module";
import {AdminLayoutComponent} from './admin-layout.component';
import {AdminQuestionsComponent} from './admin-questions/admin-questions.component';
import {AdminCoursesComponent} from './admin-courses/admin-courses.component';
import {AdminEventsComponent} from './admin-events/admin-events.component';
import {AdminPassingComponent} from './admin-passing/admin-passing.component';
import {AdminAccesesComponent} from './admin-acceses/admin-acceses.component';
import {AdminCalendarComponent} from './admin-calendar/admin-calendar.component';
@NgModule({
  declarations: [
    AdminHomeComponent,
    AdminNavbarComponent,
    AdminLayoutComponent,
    AdminQuestionsComponent,
    AdminCoursesComponent,
    AdminEventsComponent,
    AdminPassingComponent,
    AdminAccesesComponent,
    AdminCalendarComponent,
  ],
  exports: [
    AdminLayoutComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
  ]
})
export class AdminModule { }
