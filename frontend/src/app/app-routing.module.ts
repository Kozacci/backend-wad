import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {Client404Component} from "./client/client-404/client-404.component";

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./client/client-routing.module').then(m => m.ClientRoutingModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule)
  },
  { path: '404', component: Client404Component },
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
