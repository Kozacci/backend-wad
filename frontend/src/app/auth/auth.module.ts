import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {SharedModule} from "../shared/shared.module";
import {AnimateModule} from "primeng/animate";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {CarouselModule} from "primeng/carousel";
import {RatingModule} from "primeng/rating";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Error404Component} from './error-404/error-404.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {AuthLayoutComponent} from './auth-layout.component';

@NgModule({
  declarations: [
    Error404Component,
    LoginComponent,
    RegisterComponent,
    AuthLayoutComponent,
  ],
  exports: [AuthLayoutComponent],
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
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
  ]
})
export class AuthModule { }
