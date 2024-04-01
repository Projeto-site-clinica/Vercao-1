import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './componentes/sistema/login/login.component';
import { CadastrarComponent } from './componentes/sistema/cadastrar/cadastrar.component';
import { IndexComponent } from './componentes/layout/index/index.component';
import { rotaguardGuard } from './guards/rotaguard.guard';
import { PerfilComponent } from './componentes/perfil/perfil.component';
import { HomeComponent } from './componentes/home/home.component';
import { DadosComponent } from './componentes/perfil/dados/dados.component';
import { ServiciosComponent } from './componentes/perfil/servicios/servicios.component';
import { HorariosComponent } from './componentes/perfil/horarios/horarios.component';


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
    { path: "perfil/dados", component:DadosComponent },
    { path: "perfil/services", component:ServiciosComponent },
    { path: "perfil/horarios", component:HorariosComponent },
    { path: "home", component:HomeComponent } 
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
