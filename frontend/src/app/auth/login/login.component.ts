import {Component} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";
import {FormService} from "../../shared/services/form/form.service";

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
  )
  { }

  login() {
    if(this.email.valid && this.password.valid) {
      this.authService.login(this.email.value!, this.password.value!)
    }
  }

}
