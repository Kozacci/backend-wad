import {fakeAsync, tick} from '@angular/core/testing';
import {ClientMyEventsComponent} from './client-my-events.component';
import {RestClient} from "../../shared/rest-client";
import {MessageService} from "primeng/api";
import {of, throwError} from "rxjs";
import {
  EventCity, EventType,
  ParticipantEventFilterDTO
} from "../../shared/dto";
import {PathService} from "../../shared/services/path.service";

/**
 * Jasmine Unit Test to check client my events component
 */
describe('ClientMyEventsComponent', () => {
  let component: ClientMyEventsComponent;
  let pathServiceSpy: jasmine.SpyObj<PathService>;
  let restClientSpy: jasmine.SpyObj<RestClient>;
  let messageServiceSpy: jasmine.SpyObj<MessageService>;

  beforeEach(() => {
    restClientSpy = jasmine.createSpyObj('RestClient', ['getParticipantEventsByFilters', 'deleteAssigningForEvent']);
    messageServiceSpy = jasmine.createSpyObj('MessageService', ['add']);

    component = new ClientMyEventsComponent(restClientSpy, pathServiceSpy, messageServiceSpy);

    // Setting default mocks
    restClientSpy.deleteAssigningForEvent.and.returnValue(of({}));
  });

  /**
   * It checks if getParticipantEvents method properly launches
   */
  it('should update events when getParticipantEvents is called', () => {

    const mockEvents: ParticipantEventFilterDTO[] = [];

    restClientSpy.getParticipantEventsByFilters.and.returnValue(of(mockEvents));

    component.ngOnInit();

    expect(component.events).toEqual(mockEvents);
    expect(restClientSpy.getParticipantEventsByFilters).toHaveBeenCalled();
  });

  /**
   *  It checks if failure cancellation of assigning on event works properly
   *  by asserting console error
   *  TODO - create unit test for successful call of this method
   */
  it('should log an error to the console on cancelParticipantEvent failure', fakeAsync(() => {
    const errorResponse = { error: { message: 'Błąd rezygnacji z eventu' } };
    restClientSpy.deleteAssigningForEvent.and.returnValue(throwError(() => errorResponse));
    spyOn(console, 'error');

    const event = {
      eventId: -1,
      type: EventType.EVENT_DLA_FIRMY,
      city: EventCity.SOPOT,
      cost: 500,
      assignedParticipants: 16,
      maxParticipantsNumber: 15,
      isPaid: true,
      date: new Date(Date.now()),
      duration: new Date(Date.now()),
      participantEventId: -5,
      ordererLastName: '1',
      ordererEmail: 'testEmaill'
    };

    component.cancelParticipantEvent(event);
    tick();
    expect(console.error).toHaveBeenCalledWith('Błąd rezygnacji z eventu', errorResponse);
  }));
});
