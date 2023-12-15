import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {ParticipantEntityDTO, ParticipantLoginDTO, ParticipantRegisterDTO, QuestionFilterDTO} from "./dto";
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

  getQuestionsByFilters(id: number | null, content: string | null,
                        category: string | undefined, sortBy: string | undefined): Observable<QuestionFilterDTO[]> {
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
}
