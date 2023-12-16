import {Component} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";
import {FormService} from "../../shared/services/form/form.service";
import {RestClient} from "../../shared/rest-client";
import {PathService} from "../../shared/services/path.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-client-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  passwordHide: boolean = true;
  email =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.email,
        Validators.minLength(5)
      ]
    );
  password =
    new FormControl(
      '',
      [
        Validators.required,
        Validators.minLength(8)
      ]
    );


  constructor(
    public readonly authService: AuthService,
    public readonly formService: FormService,
    private restClient: RestClient,
    private readonly pathService: PathService,
    public readonly messageService: MessageService
  )
  { }

  login() {
    if(this.email.valid && this.password.valid) {
      const participantToLogin = {
        email: this.email.value?? '',
        password: this.password.value?? '',
      }
      this.restClient.login(participantToLogin)
        .subscribe(
          (response) => {
            this.pathService.navigate('/')
            this.messageService.add({life:5000, severity:'success', summary:'Logowanie', detail:"Pomyślnie zalogowano!"})
          },
          (error) => {
            this.messageService.add({life:4000, severity:'error', summary:'Logowanie', detail:"Niepoprawne dane!"})
            console.error('Błąd logowania', error);
          })
    }
  }

}
