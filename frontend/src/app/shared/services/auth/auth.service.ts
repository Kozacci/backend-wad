import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private readonly cookieService: CookieService,
    private readonly messageService: MessageService
  ) {}

   // TODO
  login(): boolean {
    return true;
  }

  // TODO
  logout(): boolean {
    return true;
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
