import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserLayoutComponent } from './user-layout.component';
import {RouterModule, Routes} from "@angular/router";
import { UserHomeComponent } from './user-home/user-home.component';

const routes: Routes = [
  {
    path: '',
    canActivate: undefined,
    component: UserHomeComponent
  }
];

@NgModule({
  declarations: [
    UserLayoutComponent,
    UserHomeComponent
  ],
  exports: [
    UserLayoutComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class UserModule { }
