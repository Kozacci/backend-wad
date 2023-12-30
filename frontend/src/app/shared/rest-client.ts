import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {
  CategoryLearningEntityDTO,
  CategoryLearningUpdateDTO,
  CourseCreateUpdateDTO,
  CourseEntityDTO,
  CourseFilterDTO,
  EventCreateUpdateDTO,
  EventEntityDTO,
  EventFilterDTO, GeneralLearningEntityDTO,
  ParticipantCourseEntityDTO,
  ParticipantCourseFilterDTO,
  ParticipantCourseUpdateDTO,
  ParticipantEntityDTO,
  ParticipantEventEntityCreateDTO,
  ParticipantEventFilterDTO,
  ParticipantLoginDTO,
  ParticipantRegisterDTO,
  ParticipantUpdateDTO,
  QuestionCreateUpdateDTO,
  QuestionEntityDTO,
  QuestionFilterDTO,
  TrialExamEntityDTO
} from "./dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestClient {

  apiUrl = 'http://localhost:8080/api';

  constructor(
    private readonly http: HttpClient
  )
  {}

  register(participantRegisterDTO: ParticipantRegisterDTO): Observable<ParticipantEntityDTO> {
    return this.http.post<ParticipantEntityDTO>(`${this.apiUrl}/auth/register`, participantRegisterDTO);
  }

  login(participantLoginDTO: ParticipantLoginDTO): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/auth/login`, participantLoginDTO, { withCredentials: true });
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/auth/logout`, {}, { withCredentials: true });
  }

  updateParticipant(participantId: number, participantUpdateDTO: ParticipantUpdateDTO): Observable<ParticipantEntityDTO> {
    return this.http.put<ParticipantEntityDTO>(`${this.apiUrl}/participants/${participantId}`, participantUpdateDTO, { withCredentials: true});
  }

  getParticipantByEmail(email: string): Observable<ParticipantEntityDTO> {
    return this.http.get<ParticipantEntityDTO>(`${this.apiUrl}/participants/${email}`, { withCredentials: true})
  }

  signInOnCourse(participantId: number, courseId: number) {
    return this.http.post<ParticipantCourseEntityDTO>(`${this.apiUrl}/participant-courses/${participantId}/sign-in/${courseId}`, null, { withCredentials: true})
  }

  signInOnEvent(participantEventCreateDTO: ParticipantEventEntityCreateDTO) {
    return this.http.post<ParticipantCourseEntityDTO>(`${this.apiUrl}/participant-events`, participantEventCreateDTO, { withCredentials: true})
  }

  getCoursesByFilters(courseType: string | undefined,
                      courseStatus: string | undefined,
                      courseCity: string  | undefined,
                      dateFrom: string | null,
                      dateTo: string | null,
                      registeredParticipants: number | null,
                      participantsLimit: number | null,
                      sortBy: string | undefined): Observable<CourseFilterDTO[]> {
    let params = new HttpParams();
    if (courseType !== null && courseType !== undefined) {
      params = params.append('courseType', courseType);
    }
    if (courseStatus !== null  && courseStatus !== undefined) {
      params = params.append('courseStatus', courseStatus);
    }
    if (courseCity !== null && courseCity !== undefined) {
      params = params.append('courseCity', courseCity);
    }
    if (dateFrom !== null) {
      params = params.append('dateFrom', dateFrom);
    }
    if (dateTo !== null) {
      params = params.append('dateTo', dateTo);
    }
    if (registeredParticipants !== null) {
      params = params.append('registeredParticipants', registeredParticipants);
    }
    if (participantsLimit !== null) {
      params = params.append('participantsLimit', participantsLimit);
    }
    if (sortBy !== null && sortBy !== undefined) {
      params = params.append('sortBy', sortBy);
    }
    return this.http.get<CourseFilterDTO[]>(`${this.apiUrl}/courses/filter-by`, {params, withCredentials: true } );
  }

  getCoursesByParticipantIdAndFilters(
    participantId: number | undefined,
    courseType: string | undefined,
    courseStatus: string | undefined,
    isPaid: boolean | null,
    isPassed: boolean | null,
    sortBy: string | undefined,
    participantEmail: string | null,
    participantLastName: string | null,
    courseDateFrom: string | null,
    courseCity: string | undefined): Observable<ParticipantCourseFilterDTO[]>
  {
    let params = new HttpParams();
    if (participantId !== null && participantId !== undefined) {
      params = params.append("participantId", participantId);
    }
    if (courseType !== null && courseType !== undefined) {
      params = params.append('courseType', courseType);
    }
    if (courseStatus !== null  && courseStatus !== undefined) {
      params = params.append('courseStatus', courseStatus);
    }
    if (isPaid !== null) {
      params = params.append('isPaid', isPaid);
    }
    if (isPassed !== null) {
      params = params.append('isPassed', isPassed);
    }
    if (sortBy !== null && sortBy !== undefined) {
      params = params.append('sortBy', sortBy);
    }
    if (participantEmail !== null && participantEmail !== undefined && participantEmail !== "") {
      params = params.append('email', participantEmail);
    }
    if (participantLastName !== null && participantLastName !== undefined && participantLastName !== "") {
      params = params.append('lastName', participantLastName);
    }
    if (courseDateFrom !== null && courseDateFrom !== undefined) {
      params = params.append('dateFrom', courseDateFrom)
    }
    if (courseCity !== null && courseCity !== undefined) {
      params = params.append('courseCity', courseCity);
    }
    return this.http.get<ParticipantCourseFilterDTO[]>(`${this.apiUrl}/participants/courses/filter-by`, {params, withCredentials: true } );
  }

  getEventsByFilters(
    type: string | undefined,
    city: string  | undefined,
    cost: number | undefined,
    maxParticipantsNumber: number | undefined,
    sortBy: string | undefined,
    adminSearch: boolean): Observable<EventFilterDTO[]>
  {
    let params = new HttpParams();
    if (type !== null && type !== undefined) {
      params = params.append('type', type);
    }
    if (city !== null && city !== undefined) {
      params = params.append('city', city);
    }
    if (cost !== null && cost !== undefined) {
      params = params.append('cost', cost);
    }
    if (maxParticipantsNumber !== null && maxParticipantsNumber !== undefined) {
      params = params.append('maxParticipantsNumber', maxParticipantsNumber);
    }
    if (sortBy !== null && sortBy !== undefined) {
      params = params.append('sortBy', sortBy);
    }
    params = params.append('adminSearch', adminSearch);
    return this.http.get<EventFilterDTO[]>(`${this.apiUrl}/events/events-filter-by`, {params, withCredentials: true } );
  }

  getParticipantEventsByFilters(type: string | undefined,
                     city: string  | undefined,
                     clientLastName: string  | undefined,
                     clientEmail: string  | undefined,
                     sortBy: string | undefined): Observable<ParticipantEventFilterDTO[]> {
    let params = new HttpParams();
    if (type !== null && type !== undefined) {
      params = params.append('type', type);
    }
    if (city !== null && city !== undefined) {
      params = params.append('city', city);
    }
    if (clientLastName !== null && clientLastName !== undefined) {
      params = params.append('clientLastName', clientLastName);
    }
    if (clientEmail !== null && clientEmail !== undefined) {
      params = params.append('clientEmail', clientEmail);
    }
    if (sortBy !== null && sortBy !== undefined) {
      params = params.append('sortBy', sortBy);
    }
    return this.http.get<ParticipantEventFilterDTO[]>(`${this.apiUrl}/events/participant-events-filter-by`, {params, withCredentials: true } );
  }

  getQuestionsByFilters(id: number | null,
                        content: string | null,
                        category: string | undefined,
                        sortBy: string | undefined): Observable<QuestionFilterDTO[]> {
    let params = new HttpParams();
    if (id !== null) {
      params = params.append('id', id);
    }
    if (content !== null  && content !== "") {
      params = params.append('content', content);
    }
    if (category !== null && category !== undefined) {
      params = params.append('category', category);
    }
    if (sortBy !== null && sortBy !== undefined) {
      params = params.append('sortBy', sortBy);
    }
    return this.http.get<QuestionFilterDTO[]>(`${this.apiUrl}/questions/filter-by`, {params, withCredentials: true } );
  }

  addQuestion(questionToAdd: QuestionCreateUpdateDTO): Observable<QuestionEntityDTO> {
    return this.http.post<QuestionEntityDTO>(`${this.apiUrl}/questions`,questionToAdd, {withCredentials: true});
  }

  editQuestion(questionToEdit: QuestionCreateUpdateDTO, questionToEditId: number):Observable<QuestionEntityDTO> {
    const url = `${this.apiUrl}/questions/${questionToEditId}`;
    return this.http.put<QuestionEntityDTO>(url, questionToEdit, {withCredentials: true});
  }

  deleteQuestionById(questionToEditId: number | null): Observable<any> {
    const url = `${this.apiUrl}/questions/${questionToEditId}`;
    return this.http.delete<QuestionEntityDTO>(url, {withCredentials: true});
  }

  addCourse(courseToAdd: CourseCreateUpdateDTO): Observable<CourseEntityDTO> {
    return this.http.post<CourseEntityDTO>(`${this.apiUrl}/courses`, courseToAdd, {withCredentials: true})
  }

  deleteCourseById(courseToDeleteId: number | null) {
    const url = `${this.apiUrl}/courses/${courseToDeleteId}`
    return this.http.delete(url, {withCredentials: true})
  }

  editCourse(courseToEdit: CourseCreateUpdateDTO, courseToEditId: number): Observable<CourseEntityDTO> {
    const url = `${this.apiUrl}/courses/${courseToEditId}`;
    return this.http.put<CourseEntityDTO>(url, courseToEdit, {withCredentials: true});
  }

  deleteAssigningForEvent(participantEventId: number | null) {
    return this.http.delete(`${this.apiUrl}/participant-events/${participantEventId}`, {withCredentials: true})
  }

  deleteAssigningForCourse(participantCourseId: number | null) {
    return this.http.delete(`${this.apiUrl}/participant-courses/${participantCourseId}`, {withCredentials: true})
  }

  addEvent(eventToAdd: EventCreateUpdateDTO): Observable<EventEntityDTO> {
    return this.http.post<EventEntityDTO>(`${this.apiUrl}/events`, eventToAdd, {withCredentials: true})
  }

  editEvent(eventToEdit: EventCreateUpdateDTO, eventId: number): Observable<EventEntityDTO> {
    const url = `${this.apiUrl}/events/${eventId}`;
    return this.http.put<EventEntityDTO>(url, eventToEdit, {withCredentials: true});
  }

  deleteEventById(eventToDeleteId: number | null) {
    const url = `${this.apiUrl}/events/${eventToDeleteId}`;
    return this.http.delete(url, {withCredentials: true})
  }

  updateCoursePassing(participantCourseUpdateDTO: ParticipantCourseUpdateDTO): Observable<ParticipantCourseEntityDTO[]> {
    return this.http.put<ParticipantCourseEntityDTO[]>(`${this.apiUrl}/participant-courses`, participantCourseUpdateDTO, {withCredentials: true});
  }

  getRandomQuestionByCategories(categories: string[]): Observable<QuestionEntityDTO> {
    const params = new HttpParams().set('categories', categories.join(','));
    return this.http.get<QuestionEntityDTO>(`${this.apiUrl}/questions/random`, { params, withCredentials: true });
  }

  updateTrialExamLearning(participantCourseId: number | null, isPassed: boolean) {
    const params = new HttpParams().set('isPassed', isPassed);
    return this.http.put<TrialExamEntityDTO>(`${this.apiUrl}/learning/${participantCourseId}/trial-exam`, null, { params, withCredentials: true } )
  }

  updateCategoryLearning(participantCourseId: number | null, dto: CategoryLearningUpdateDTO) {
    return this.http.put<CategoryLearningEntityDTO>(`${this.apiUrl}/learning/${participantCourseId}/category-learning`, dto, { withCredentials: true })
  }

  updateGeneralLearning(participantCourseId: number | null, isCorrectAnswer: boolean) {
    const params = new HttpParams().set('isCorrectAnswer', isCorrectAnswer);
    return this.http.put<GeneralLearningEntityDTO>(`${this.apiUrl}/learning/${participantCourseId}/general-learning`, null, { params, withCredentials: true })
  }

  getParticipantCourseById(participantCourseId: number | null) {
    return this.http.get<ParticipantCourseEntityDTO>(`${this.apiUrl}/participant-courses/${participantCourseId}`, { withCredentials: true });
  }

}
