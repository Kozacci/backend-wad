import {Component, OnInit} from '@angular/core';
import {EventFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-my-events',
  templateUrl: './client-my-events.component.html',
  styleUrls: ['./client-my-events.component.css']
})
export class ClientMyEventsComponent implements OnInit {

  events: EventFilterDTO[] = [];

  constructor(private readonly restClient: RestClient,
              private readonly pathService: PathService) {
  }

  ngOnInit() {
    this.getParticipantEvents();
  }

  getParticipantEvents() {
    const email = sessionStorage.getItem('cacheEmail');
    return this.restClient
      .getParticipantEventsByFilters(undefined, undefined, undefined, email!, 'date')
      .subscribe(response => {
        this.events = response;
        console.log(response);
      })
  }

  // TODO 1 -- my courses + my events + navigate after signing on events/courses
  // TODO 2 -- e-learning (stats, results, learning with 3 types of it)

  getImage(event: EventFilterDTO): string {
    if(event.type == 'PANIENSKI') {
      return 'assets/images/client/overlay/events/event-1.png';
    }
    if(event.type == 'KAWALERSKI') {
      return 'assets/images/client/overlay/events/event-3.png';
    }
    if(event.type == 'REJS_WIDOKOWY') {
      return 'assets/images/client/overlay/events/event-4.png';
    }
    if(event.type == 'EVENT_DLA_FIRMY') {
      return 'assets/images/client/overlay/events/event-5.png';
    }
    return 'assets/images/client/overlay/events/event-6.png';
  }

}
