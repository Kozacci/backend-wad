import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ClientLayoutComponent} from './client-layout.component';
import {ClientHomeComponent} from './client-home/client-home.component';
import {ClientNavbarComponent} from './client-navbar/client-navbar.component';
import {SharedModule} from "../shared/shared.module";
import {AnimateModule} from "primeng/animate";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {CarouselModule} from "primeng/carousel";
import {RatingModule} from "primeng/rating";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ClientFooterComponent } from './client-footer/client-footer.component';
import { Client404Component } from './client-404/client-404.component';
import { ClientLoginComponent } from './client-login/client-login.component';

@NgModule({
  declarations: [
    ClientLayoutComponent,
    ClientHomeComponent,
    ClientNavbarComponent,
    ClientFooterComponent,
    Client404Component,
    ClientLoginComponent
  ],
  exports: [
    ClientLayoutComponent,
  ],
  imports: [
    SharedModule,
    CommonModule,
    NgOptimizedImage,
    AnimateModule,
    MatMenuModule,
    MatButtonModule,
    CarouselModule,
    RatingModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class ClientModule { }
