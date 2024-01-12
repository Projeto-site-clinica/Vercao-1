import { Component, inject } from '@angular/core';
import { LoginService } from '../../../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { Login } from '../../../models/login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-secretaria',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  login: Login = new Login();
  roteador = inject(Router);
  loginService = inject(LoginService);
  toastr = inject(ToastrService);

  constructor() {
   this.loginService.removerToken();
  }

  logar() {
    this.loginService.logar(this.login).subscribe({
      next: usuario => {
        this.loginService.addToken(usuario.token);
        this.roteador.navigate(['perfil']);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
      }
    });
  }
}