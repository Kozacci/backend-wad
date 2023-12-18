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

  login(email: string, password: string) {
      const participantToLogin = {
        email: email,
        password: password
      }
      this.restClient.login(participantToLogin)
        .subscribe(
          (response) => {
            this.pathService.navigate('/');
            this.messageService.add({life:4000, severity:'success', summary:'Logowanie', detail:"Pomyślnie zalogowano!"});
            this.restClient.getParticipantByEmail(participantToLogin.email)
              .subscribe(
                response => {
                  sessionStorage.setItem('cacheId', response.id.toString())
                  sessionStorage.setItem('cacheEmail', response.email)
                }
              )
          },
          (error) => {
            this.messageService.add({life:4000, severity:'error', summary:'Logowanie', detail:"Niepoprawne dane!"})
            console.error('Błąd logowania', error);
          })
  }


  logout() {
    this.restClient.logout().subscribe();
    this.pathService.navigate('/');
    this.messageService.add({life:5000, severity:'success', summary:'Wylogowanie', detail:"Udało Ci się wylogować!"})
  }

  isLogged(): boolean {
    const token = this.cookieService.get("JWT");
    return !!token;
  }

  messageIfNotLogged() {
    this.messageService.add({life:4000, severity:'error', summary:'Login', detail:"Nie jesteś zalogowany!"})
  }

}
