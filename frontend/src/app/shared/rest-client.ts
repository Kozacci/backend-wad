import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ParticipantEntityDTO, ParticipantLoginDTO, ParticipantRegisterDTO} from "./dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestClient {

  apiUrl = 'http://localhost:8080';

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

}
