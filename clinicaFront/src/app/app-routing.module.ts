import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './componentes/sistema/login/login.component';
import { CadastrarComponent } from './componentes/sistema/cadastrar/cadastrar.component';
import { IndexComponent } from './componentes/layout/index/index.component';
import { rotaguardGuard } from './guards/rotaguard.guard';
import { PerfilComponent } from './componentes/perfil/perfil.component';
import { HomeComponent } from './componentes/home/home.component';


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
    { path: "perfil", component:PerfilComponent },
    { path: "home", component:HomeComponent } 
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
