import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/layout/header/header.component';
import { FooterComponent } from './componentes/layout/footer/footer.component';
import { IndexComponent } from './componentes/layout/index/index.component';
import { LoginComponent } from './componentes/sistema/login/login.component';
import { CadastrarComponent } from './componentes/sistema/cadastrar/cadastrar.component';
import { PerfilComponent } from './componentes/perfil/perfil.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule, provideToastr } from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule, provideAnimations } from '@angular/platform-browser/animations';
import { httpInterceptorProviders } from './interceptors/httpinterceptor.service';
import { HomeComponent } from './componentes/home/home.component';
import { AgendamentosComponent } from './componentes/agendamentos/agendamentos.component';
import { DoutorDetalhesComponent } from './componentes/perfil/doutor-detalhes/doutor-detalhes.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    IndexComponent,
    LoginComponent,
    CadastrarComponent,
    PerfilComponent,
    HomeComponent,
    AgendamentosComponent,
    DoutorDetalhesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    RouterModule,
    NgbModule
  ],
  providers: [
    httpInterceptorProviders,
    provideAnimations(),
    provideToastr()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
