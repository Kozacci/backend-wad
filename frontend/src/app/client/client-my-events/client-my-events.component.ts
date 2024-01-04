import {Component, OnInit} from '@angular/core';
import {EventType, ParticipantEventFilterDTO} from "../../shared/dto";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";
import {MessageService} from "primeng/api";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-client-my-events',
  templateUrl: './client-my-events.component.html',
  styleUrls: ['./client-my-events.component.css']
})
export class ClientMyEventsComponent implements OnInit{

  events: ParticipantEventFilterDTO[] = [];

  constructor(private readonly restClient: RestClient,
              private readonly pathService: PathService,
              private readonly messageService: MessageService
  ) { }

  ngOnInit() {
    this.getParticipantEvents();
  }

  getParticipantEvents() {
    const email = sessionStorage.getItem('cacheEmail');
    return this.restClient
      .getParticipantEventsByFilters(undefined, undefined, undefined, email!, 'date')
      .subscribe(response => {
        if(response != null) {
          this.events = response;
          console.log(response);
        }
        else {
          this.events = [];
          console.log(response);
        }
      })
  }

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
    switch (event.type) {
      case EventType.PANIENSKI:
        return this.pathService.navigate('/oferta/eventy/wieczor-panienski')
      case EventType.KAWALERSKI:
        return this.pathService.navigate('/oferta/eventy/wieczor-kawalerski')
      case EventType.REJS_WIDOKOWY:
        return this.pathService.navigate('/oferta/eventy/rejs-widokowy')
      case EventType.EVENT_DLA_FIRMY:
        return this.pathService.navigate('/oferta/eventy/event-dla-firmy')
      default:
        return this.pathService.navigate('/oferta/eventy/wynajem-skutera')
    }
  }

  cancelParticipantEvent(event: ParticipantEventFilterDTO) {
    this.restClient.deleteAssigningForEvent(event.participantEventId)
      .pipe(
        switchMap(async () => this.getParticipantEvents()) // asynchronic function call due to getParticipantEvents Bug - it was not called after cancellation
      )
      .subscribe(
        () => {
          this.messageService.add({life:5000, severity:'success', summary:'Rezygnacja z eventu', detail:"Pomyślnie udało Ci się zrezygnować z eventu"})
        },
        (error) => {
          this.messageService.add({life:4000, severity:'error', summary:'Rezygnacja z eventu', detail: error.message})
          console.error('Błąd rezygnacji z eventu', error);
        })
  }

}
