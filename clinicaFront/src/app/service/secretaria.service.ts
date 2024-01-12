import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Secretaria } from '../models/secretaria';
import { Mensagem } from '../models/mensagem';

@Injectable({
  providedIn: 'root'
})
export class SecretariaService {
  API: string = 'http://localhost:8080/secretaria';
  http = inject(HttpClient);

  constructor() { }

  buscarPorId(id: number): Observable<Secretaria> {
    return this.http.get<Secretaria>(this.API + "/" + `${id}`);
  }

  listar(): Observable<Secretaria[]> {
    return this.http.get<Secretaria[]>(`${this.API}` + "/lista");
  }

  save(secretaria: Secretaria): Observable<Mensagem> {
    if (secretaria.id) {
      return this.http.put<Mensagem>(this.API+"/"+`${secretaria.id}`, secretaria);
    } else {
      return this.http.post<Mensagem>(this.API, secretaria);
    }
  }

  deletar(id: number): Observable<Mensagem> {
    return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
  }
}