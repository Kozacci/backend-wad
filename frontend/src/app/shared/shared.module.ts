import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";
import {TextIconComponent} from './text-icon/text-icon.component';
import {SidebarModule} from "primeng/sidebar";
import {DividerModule} from "primeng/divider";
import {PrimaryButtonComponent} from './primary-button/primary-button.component';
import {SecondaryButtonComponent} from './secondary-button/secondary-button.component';
import {ToastModule} from "primeng/toast";
import {MenubarModule} from "primeng/menubar";
import {HttpClientModule} from "@angular/common/http";
import {TableModule} from "primeng/table";
import {StyleClassModule} from "primeng/styleclass";
import {RippleModule} from "primeng/ripple";


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
    SecondaryButtonComponent,
    CommonModule,
    ButtonModule,
    SidebarModule,
    ToastModule,
    MenubarModule,
    HttpClientModule,
    TableModule,
    StyleClassModule,
    RippleModule,
  ]
})
export class SharedModule { }
