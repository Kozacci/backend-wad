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
import {
  ClientEventDetailsBacheloretteComponent
} from "./offer/client-event-details/client-event-details-bachelorette/client-event-details-bachelorette.component";
import {
  ClientEventDetailsBachelorComponent
} from "./offer/client-event-details/client-event-details-bachelor/client-event-details-bachelor.component";
import {
  ClientEventDetailsBusinessComponent
} from "./offer/client-event-details/client-event-details-business/client-event-details-business.component";
import {
  ClientEventDetailsSightseeingComponent
} from "./offer/client-event-details/client-event-details-sightseeing/client-event-details-sightseeing.component";
import {
  ClientEventDetailsScooterComponent
} from "./offer/client-event-details/client-event-details-scooter/client-event-details-scooter.component";
import {ClientMyProfileComponent} from "./client-my-profile/client-my-profile.component";
import {ClientFaqComponent} from "./client-about/client-faq/client-faq.component";
import {ClientContactComponent} from "./client-about/client-contact/client-contact.component";
import {ClientMyEventsComponent} from "./client-my-events/client-my-events.component";
import {ClientMyCoursesComponent} from "./client-my-courses/client-my-courses.component";
import {ClientElearningComponent} from "./client-elearning/client-elearning.component";
import {
  ClientGeneralLearningComponent
} from "./client-elearning/client-general-learning/client-general-learning.component";

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
  {
    path: 'oferta/eventy/wieczor-kawalerski',
    canActivate: undefined,
    component: ClientEventDetailsBachelorComponent
  },
  {
    path: 'oferta/eventy/wieczor-panienski',
    canActivate: undefined,
    component: ClientEventDetailsBacheloretteComponent
  },
  {
    path: 'oferta/eventy/event-dla-firmy',
    canActivate: undefined,
    component: ClientEventDetailsBusinessComponent
  },
  {
    path: 'oferta/eventy/rejs-widokowy',
    canActivate: undefined,
    component: ClientEventDetailsSightseeingComponent
  },
  {
    path: 'oferta/eventy/wynajem-skutera',
    canActivate: undefined,
    component: ClientEventDetailsScooterComponent
  },
  {
    path: 'profil',
    canActivate: undefined,
    component: ClientMyProfileComponent
  },
  {
    path: 'kontakt',
    canActivate: undefined,
    component: ClientContactComponent
  },
  {
    path: 'najczesciej-zadawane-pytania',
    canActivate: undefined,
    component: ClientFaqComponent
  },
  {
    path: 'profil/moje-eventy',
    canActivate: undefined,
    component: ClientMyEventsComponent
  },
  {
    path: 'profil/moje-kursy',
    canActivate: undefined,
    component: ClientMyCoursesComponent
  },
  {
    path: 'e-learning',
    canActivate: undefined,
    component: ClientElearningComponent
  },
  {
    path: 'e-learning/nauka-ogolna/:id',
    canActivate: undefined,
    component: ClientGeneralLearningComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
