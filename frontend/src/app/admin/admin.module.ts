import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {RouterModule, Routes} from "@angular/router";
import {AdminNavbarComponent} from './admin-navbar/admin-navbar.component';
import {SharedModule} from "../shared/shared.module";

const routes: Routes = [
  {
    path: '',
    canActivate: undefined,
    component: AdminHomeComponent
  }
]

@NgModule({
  declarations: [
    AdminHomeComponent,
    AdminNavbarComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(routes)
  ]
})
export class AdminModule { }
