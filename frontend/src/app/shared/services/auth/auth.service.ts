import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {MessageService} from "primeng/api";
import {RestClient} from "../../rest-client";
import {PathService} from "../path.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private readonly cookieService: CookieService,
    private readonly restClient: RestClient,
    private readonly messageService: MessageService,
    private readonly pathService: PathService
  ) {}

  logout() {
    this.restClient.logout().subscribe();
    this.pathService.navigate('/');
  }

  isLogged(): boolean {
    const token = this.cookieService.get("JWT");
    return !!token;
  }

  messageIfNotLogged() {
    this.messageService.add({life:4000, severity:'error', summary:'Login', detail:"Nie jesteś zalogowany!"})
  }

}
