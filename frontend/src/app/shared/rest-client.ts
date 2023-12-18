import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {
  CourseFilterDTO,
  ParticipantEntityDTO,
  ParticipantLoginDTO,
  ParticipantRegisterDTO,
  QuestionCreateUpdateDTO,
  QuestionEntityDTO,
  QuestionFilterDTO
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

  getCourses(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/courses`, {}, { withCredentials: true });
  }

  getCoursesByFilters(courseType: string | null,
                      courseStatus: string | null,
                      courseCity: string | null,
                      dateFrom: Date | null,
                      dateTo: Date | null,
                      registeredParticipants: number | null,
                      participantsLimit: number | null,
                      sortBy: string | null): Observable<CourseFilterDTO[]> {
    let params = new HttpParams();
    if (courseType !== null && courseType !== "") {
      params = params.append('courseType', courseType);
    }
    if (courseStatus !== null  && courseStatus !== "") {
      params = params.append('courseStatus', courseStatus);
    }
    if (courseCity !== null && courseCity !== "") {
      params = params.append('courseCity', courseCity);
    }
    if (dateFrom !== null) {
      params = params.append('dateFrom', dateFrom.toISOString());
    }
    if (dateTo !== null) {
      params = params.append('dateTo', dateTo.toISOString());
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
}
