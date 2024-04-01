import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Login } from 'src/app/models/login';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
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
        this.roteador.navigate(['home']);
      },
      error: erro => {
        !this.toastr.error(erro.error.mensagem);
      }
    });
  }

}
