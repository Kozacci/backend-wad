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

@NgModule({
  declarations: [
    ClientLayoutComponent,
    ClientHomeComponent,
    ClientNavbarComponent
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

    ]
})
export class ClientModule { }
