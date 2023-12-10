import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Category, ParticipantEntityDTO, ParticipantLoginDTO, ParticipantRegisterDTO, QuestionFilterDTO} from "./dto";
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
    return this.http.post<any>(`${this.apiUrl}/auth/login`, participantLoginDTO, { observe: 'response' });
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/auth/logout`, {});
  }

  getQuestionsByFilters(id: number, content: string, category: Category, sortBy: string): Observable<QuestionFilterDTO[]> {
    let params = new HttpParams();
    if (this.walidacjaUtilsNiePusty(id)) {
      params = params.append('id', id);
    }
    params = params.append('id', 100);
    if (this.walidacjaUtilsNiePusty(content)) {
      params = params.append('content', content);
    }
    if (this.walidacjaUtilsNiePusty(category)) {
      params = params.append('category', category);
    }
    if (this.walidacjaUtilsNiePusty(sortBy)) {
      params = params.append('sortBy', sortBy);
    }
    return this.http.get<QuestionFilterDTO[]>(`${this.apiUrl}/questions/filter-by`, {params} );
  }


  private walidacjaUtilsNiePusty(value: any): boolean {
    return !this.pusty(value);
  }

  private pusty(value: any): boolean {
    return value == null || value === undefined;
  }
}
