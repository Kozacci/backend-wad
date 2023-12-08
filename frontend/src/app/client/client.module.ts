import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientLayoutComponent } from './client-layout.component';
import {RouterModule, Routes} from "@angular/router";
import { ClientHomeComponent } from './client-home/client-home.component';
import { ClientNavbarComponent } from './client-navbar/client-navbar.component';

const routes: Routes = [
  {
    path: '',
    canActivate: undefined,
    component: ClientHomeComponent
  }
];

@NgModule({
  declarations: [
    ClientLayoutComponent,
    ClientHomeComponent,
    ClientNavbarComponent
  ],
  exports: [
    ClientLayoutComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class ClientModule { }
