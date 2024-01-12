import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './components/layout/index/index.component';
import { rotaguardGuard } from './guards/rotaguard.guard';
import { PerfilComponent } from './components/perfil/perfil.component';
import { LoginComponent } from './components/sistema/login/login.component';
import { CadastrarComponent } from './components/sistema/cadastrar/cadastrar.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: 'full'},
  { path: "login", component: LoginComponent },
  { path: "cadastrar", component: CadastrarComponent },
  { 
    path: "", 
    component:IndexComponent,
    canActivate: [rotaguardGuard], 
    data: { roles: ['PACIENTE'] }, 
    children:[
    { path: "perfil", component:PerfilComponent }
  ]},
  { 
    path: "", 
    component:IndexComponent,
    canActivate: [rotaguardGuard], 
    data: { roles: ['DOUTOR'] }, 
    children:[
    { path: "perfil", component:PerfilComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
