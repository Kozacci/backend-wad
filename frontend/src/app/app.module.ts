import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ClientModule} from "./client/client.module";
import {AdminModule} from "./admin/admin.module";
import {MessageService} from "primeng/api";
import {ClientRoutingModule} from "./client/client-routing.module";
import {AdminRoutingModule} from "./admin/admin-routing.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    ClientModule,
    AdminModule,
    AppRoutingModule,
    ClientRoutingModule,
    AdminRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
