import {Component, OnInit} from '@angular/core';
import {EventFilterDTO, EventType, ParticipantEventFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-my-events',
  templateUrl: './client-my-events.component.html',
  styleUrls: ['./client-my-events.component.css']
})
export class ClientMyEventsComponent implements OnInit {

  events: ParticipantEventFilterDTO[] = [];

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

  getImage(event: ParticipantEventFilterDTO): string {
    switch (event.type) {
      case EventType.PANIENSKI:
        return 'assets/images/client/overlay/events/event-1.png';
      case EventType.KAWALERSKI:
        return 'assets/images/client/overlay/events/event-3.png';
      case EventType.REJS_WIDOKOWY:
        return 'assets/images/client/overlay/events/event-4.png';
      case EventType.EVENT_DLA_FIRMY:
        return 'assets/images/client/overlay/events/event-5.png';
      default:
        return 'assets/images/client/overlay/events/event-6.png';
    }
  }

  getDate(event: ParticipantEventFilterDTO): string {
    return event.date.toString().split('T')[0];
  }

  getHours(event: ParticipantEventFilterDTO): string {
    return event.date.toString().split('T')[1];
  }

  goToEventDetails(event: ParticipantEventFilterDTO) {

  }

  cancelParticipantEvent(event: ParticipantEventFilterDTO) {

  }

}
