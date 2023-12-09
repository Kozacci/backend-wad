import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {MessageService} from "primeng/api";
import {RestClient} from "../../rest-client";
import {ParticipantLoginDTO, ParticipantRegisterDTO} from "../../dto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private readonly cookieService: CookieService,
    private readonly messageService: MessageService,
    private readonly restClient: RestClient
  ) {}

  // TODO
  register(user: ParticipantRegisterDTO) {
    this.restClient.register(user);
  }

   // TODO
  login(loginUser: ParticipantLoginDTO) {
    this.restClient.login(loginUser);
  }

  // TODO
  logout() {
    this.restClient.logout();
  }

  // TODO
  isLogged(): boolean {
    const token = this.cookieService.get("JWT");
    return !!token;
  }

  // TODO
  messageIfNotLogged() {
    this.messageService.add({life:4000, severity:'error', summary:'Login', detail:"Nie jesteś zalogowany!"})
  }

}
