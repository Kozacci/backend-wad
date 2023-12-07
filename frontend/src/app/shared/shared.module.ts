import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";
import { TextIconComponent } from './text-icon/text-icon.component';
import {SidebarModule} from "primeng/sidebar";
import {DividerModule} from "primeng/divider";


@NgModule({
  declarations: [
    TextIconComponent
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
    TextIconComponent
  ]
})
export class SharedModule { }
