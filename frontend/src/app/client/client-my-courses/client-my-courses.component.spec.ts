import {ClientMyCoursesComponent} from './client-my-courses.component';
import {ClientMyCoursesService} from './client-my-courses.service';
import {MessageService} from "primeng/api";
import {of, throwError} from "rxjs";
import {RestClient} from "../../shared/rest-client";
import {CourseCity, CourseStatus, CourseType, ParticipantCourseFilterDTO} from "../../shared/dto";
import {fakeAsync, tick} from "@angular/core/testing";

/**
 * Jasmine Unit Test to check client my courses component and service
 */
describe('ClientMyCoursesComponent', () => {
  let component: ClientMyCoursesComponent;
  let clientMyCoursesServiceSpy: jasmine.SpyObj<ClientMyCoursesService>;
  let restClientSpy: jasmine.SpyObj<RestClient>;
  let messageServiceSpy: jasmine.SpyObj<MessageService>;

  beforeEach(() => {
    restClientSpy = jasmine.createSpyObj('RestClient', ['deleteAssigningForCourse']);
    messageServiceSpy = jasmine.createSpyObj('MessageService', ['add']);

    clientMyCoursesServiceSpy = jasmine.createSpyObj('ClientMyCoursesService', ['getParticipantCourses']);
    component = new ClientMyCoursesComponent(restClientSpy, messageServiceSpy, clientMyCoursesServiceSpy);

    // Setting default mocks
    clientMyCoursesServiceSpy.getParticipantCourses.and.returnValue(of([]));
    restClientSpy.deleteAssigningForCourse.and.returnValue(of({}));
  });

  /**
   * It checks if getParticipantCourses service method properly launches
   *
   */
  it('should update courses when getParticipantCourses is called', () => {
    const mockCourses: ParticipantCourseFilterDTO[] = [];

    component.getParticipantCourses();

    expect(component.courses).toEqual(mockCourses);
    expect(clientMyCoursesServiceSpy.getParticipantCourses).toHaveBeenCalled();
  });

  /**
   *  It checks if cancellation of assigning on course works properly,
   *  and it updates component courses field when it is called
   */
  it('should call deleteAssigningForCourse and refresh courses on successful cancellation', fakeAsync(() => {
    const course = {
      id: 121,
      dateFrom: new Date(2023, 12, 24),
      dateTo: new Date(2023, 12, 26),
      courseStatus: CourseStatus.NIEROZPOCZETY,
      maxParticipantsNumber: 15,
      city: CourseCity.OLECKO,
      courseType: CourseType.WARSZTATY_NAWIGACYJNE,
      registeredParticipants: 13,
      participantCourseId: 123,
      participantId: 122,
      isPassed: false,
      isPaid: false,
      participantFirstName: 'Test123',
      participantLastName: 'Test123',
      participantEmail: 'test123@op.pl',
      participantPhoneNumber: '123123123',
      hasAccess: false
    };

    component.cancelParticipantCourse(course);

    expect(restClientSpy.deleteAssigningForCourse).toHaveBeenCalledWith(123);
    tick(); // it simulates time-lapse for ansynchronous methods/calls

    expect(clientMyCoursesServiceSpy.getParticipantCourses).toHaveBeenCalled();
    expect(messageServiceSpy.add).toHaveBeenCalledWith(jasmine.objectContaining({
      severity: 'success',
      summary: 'Rezygnacja z kursu',
      detail: 'Pomyślnie udało Ci się zrezygnować z kursu'
    }));
  }));

  /**
   *  It checks if failure cancellation of assigning on course works properly
   *  by asserting console error
   */
  it('should display an error message on cancellation failure', fakeAsync(() => {
    const errorResponse = { error: { message: 'Błąd rezygnacji z kursu' } };
    restClientSpy.deleteAssigningForCourse.and.returnValue(throwError(() => errorResponse));
    spyOn(console, 'error');

    const course = {
      id: 121,
      dateFrom: new Date(2023, 12, 24),
      dateTo: new Date(2023, 12, 26),
      courseStatus: CourseStatus.ROZPOCZETY,
      maxParticipantsNumber: 15,
      city: CourseCity.OLECKO,
      courseType: CourseType.WARSZTATY_NAWIGACYJNE,
      registeredParticipants: 13,
      participantCourseId: 123,
      participantId: 122,
      isPassed: false,
      isPaid: true,
      participantFirstName: 'Test123',
      participantLastName: 'Test123',
      participantEmail: 'test123@op.pl',
      participantPhoneNumber: '123123123',
      hasAccess: false
    };

    component.cancelParticipantCourse(course);
    tick();
    expect(console.error).toHaveBeenCalledWith('Błąd rezygnacji z kursu', errorResponse);
  }));

});
