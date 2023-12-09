import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";
import {TextIconComponent} from './text-icon/text-icon.component';
import {SidebarModule} from "primeng/sidebar";
import {DividerModule} from "primeng/divider";
import {ToastModule} from "primeng/toast";
import {MenubarModule} from "primeng/menubar";


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
    TextIconComponent,
    CommonModule,
    ButtonModule,
    SidebarModule,
    ToastModule,
    MenubarModule,
  ]
})
export class SharedModule { }
