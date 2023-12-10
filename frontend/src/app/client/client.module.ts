import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ClientLayoutComponent} from './client-layout.component';
import {ClientHomeComponent} from './client-home/client-home.component';
import {ClientNavbarComponent} from './client-navbar/client-navbar.component';
import {SharedModule} from "../shared/shared.module";
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
    ]
})
export class ClientModule { }
