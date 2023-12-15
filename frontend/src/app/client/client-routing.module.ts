import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientHomeComponent} from "./client-home/client-home.component";
import {Client404Component} from "./client-404/client-404.component";
import {ClientLoginComponent} from "./client-login/client-login.component";
import {ClientRegisterComponent} from "./client-register/client-register.component";

const routes: Routes = [
  {
    path: '',
    canActivate: undefined,
    component: ClientHomeComponent
  },
  { path: 'zaloguj', component: ClientLoginComponent },
  { path: 'rejestracja', component: ClientRegisterComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
