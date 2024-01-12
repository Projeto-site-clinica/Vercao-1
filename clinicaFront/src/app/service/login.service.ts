import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../models/login';
import { Usuario } from '../models/usuario';
import { JwtPayload, jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  API: string = 'http://localhost:8080/login';
  http = inject(HttpClient);

  constructor() { }


  logar(login: Login): Observable<Usuario> {
    return this.http.post<Usuario>(this.API, login);
  }

  deslogar(): Observable<any> {
    return this.http.get<any>(this.API+'/deslogar');
  }

  addToken(token: string){
    localStorage.setItem('token', token);
  }

  removerToken(){
    localStorage.removeItem('token');
  }

  getToken(){
    return localStorage.getItem('token');
  }

  getUser(){
    return this.decodificarToken() as Usuario;
  }

  decodificarToken(){
    let token = this.getToken();
    if (token) {
      return jwtDecode<JwtPayload>(token);
    }
    return "";
  }
}
