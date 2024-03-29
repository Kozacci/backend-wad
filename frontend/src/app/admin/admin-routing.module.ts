import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {AdminQuestionsComponent} from "./admin-questions/admin-questions.component";
import {AdminCoursesComponent} from "./admin-courses/admin-courses.component";
import {AdminEventsComponent} from "./admin-events/admin-events.component";
import {AdminPassingComponent} from "./admin-passing/admin-passing.component";
import {AdminAccessesComponent} from "./admin-acceses/admin-accesses.component";
import {AdminCalendarComponent} from "./admin-calendar/admin-calendar.component";

const routes: Routes = [
  {
    path: '',
    component: AdminHomeComponent
  },
  {
    path: 'questions',
    component: AdminQuestionsComponent
  },
  {
    path: 'courses',
    component: AdminCoursesComponent
  },
  {
    path: 'events',
    component: AdminEventsComponent
  },
  {
    path: 'passing',
    component: AdminPassingComponent
  },
  {
    path: 'accesses',
    component: AdminAccessesComponent
  },
  {
    path: 'calendar',
    component: AdminCalendarComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
