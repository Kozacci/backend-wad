import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {Error404Component} from "./auth/error-404/error-404.component";

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./client/client-routing.module').then(m => m.ClientRoutingModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule)
  },
  {
    path: 'autoryzacja',
    loadChildren: () => import('./auth/auth-routing.module').then(m => m.AuthRoutingModule)
  },
  {
    path: 'autoryzacja/error-404',
    component: Error404Component
  },
  {
    path: '**',
    redirectTo: 'autoryzacja/error-404'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
