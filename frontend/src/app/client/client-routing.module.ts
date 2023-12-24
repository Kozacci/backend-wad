import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientHomeComponent} from "./client-home/client-home.component";
import {ClientCoursesComponent} from "./offer/client-courses/client-courses.component";
import {
  ClientCourseDetailsSteersmanComponent
} from "./offer/client-course-details/client-course-details-steersman/client-course-details-steersman.component";
import {
  ClientCourseDetailsYachtComponent
} from "./offer/client-course-details/client-course-details-yacht/client-course-details-yacht.component";
import {
  ClientCourseDetailsSeaComponent
} from "./offer/client-course-details/client-course-details-sea/client-course-details-sea.component";
import {
  ClientCourseDetailsSailorComponent
} from "./offer/client-course-details/client-course-details-sailor/client-course-details-sailor.component";
import {
  ClientCourseDetailsWorkshopComponent
} from "./offer/client-course-details/client-course-details-workshop/client-course-details-workshop.component";
import {
  ClientCourseDetailsInternshipComponent
} from "./offer/client-course-details/client-course-details-internship/client-course-details-internship.component";
import {ClientEventsComponent} from "./offer/client-events/client-events.component";

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
  {
    path: 'oferta/kursy/jachtowy-sternik',
    canActivate: undefined,
    component: ClientCourseDetailsYachtComponent
  },
  {
    path: 'oferta/kursy/morski-sternik',
    canActivate: undefined,
    component: ClientCourseDetailsSeaComponent
  },
  {
    path: 'oferta/kursy/zeglarz-jachtowy',
    canActivate: undefined,
    component: ClientCourseDetailsSailorComponent
  },
  {
    path: 'oferta/kursy/warsztaty-nawigacyjne',
    canActivate: undefined,
    component: ClientCourseDetailsWorkshopComponent
  },
  {
    path: 'oferta/kursy/rejsy-stazowe',
    canActivate: undefined,
    component: ClientCourseDetailsInternshipComponent
  },
  {
    path: 'oferta/eventy',
    canActivate: undefined,
    component: ClientEventsComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
