import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastrarPacienteDetalhesComponent } from './components/sistema/cadastrar/cadastrar-paciente-detalhes/cadastrar-paciente-detalhes.component';
import { CadastrarDoutorDetalhesComponent } from './components/sistema/cadastrar/cadastrar-doutor-detalhes/cadastrar-doutor-detalhes.component';
import { CadastrarClinicaDetalhesComponent } from './components/sistema/cadastrar/cadastrar-clinica-detalhes/cadastrar-clinica-detalhes.component';
import { CadastrarSecretariaDetalhesComponent } from './components/sistema/cadastrar/cadastrar-secretaria-detalhes/cadastrar-secretaria-detalhes.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { IndexComponent } from './components/layout/index/index.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import { httpInterceptorProviders } from './interceptors/httpinterceptor.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './components/sistema/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    PerfilComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    RouterModule,
    NgbModule
  ],
  providers: [
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
