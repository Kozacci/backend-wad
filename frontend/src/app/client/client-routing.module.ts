import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientHomeComponent} from "./client-home/client-home.component";
import {ClientCoursesComponent} from "./client-courses/client-courses.component";
import {
  ClientCourseDetailsSteersmanComponent
} from "./client-course-details/client-course-details-steersman/client-course-details-steersman.component";

const routes: Routes = [
  {
    path: '',
    canActivate: undefined,
    component: ClientHomeComponent
  },
  {
    path: 'oferta/kursy',
    canActivate: undefined,
    component: ClientCoursesComponent
  },
  {
    path: 'oferta/kursy/sternik-motorowodny',
    canActivate: undefined,
    component: ClientCourseDetailsSteersmanComponent
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
