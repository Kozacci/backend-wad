import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";
import { TextIconComponent } from './text-icon/text-icon.component';
import {SidebarModule} from "primeng/sidebar";
import {DividerModule} from "primeng/divider";
import { PrimaryButtonComponent } from './primary-button/primary-button.component';
import { SecondaryButtonComponent } from './secondary-button/secondary-button.component';


@NgModule({
  declarations: [
    TextIconComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    SidebarModule,
  ],
  exports: [
    ButtonModule,
    SidebarModule,
    DividerModule,
    TextIconComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent
  ]
})
export class SharedModule { }
