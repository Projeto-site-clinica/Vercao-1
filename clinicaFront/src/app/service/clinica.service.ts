import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Mensagem } from '../models/mensagem';
import { Clinica } from '../models/clinica';

@Injectable({
  providedIn: 'root'
})
export class ClinicaService {
  API: string = 'http://localhost:8080/clinica';
  http = inject(HttpClient);

  constructor() { }

  buscarPorId(id: number): Observable<Clinica> {
    return this.http.get<Clinica>(this.API + "/" + `${id}`);
  }

  listar(): Observable<Clinica[]> {
    return this.http.get<Clinica[]>(`${this.API}` + "/lista");
  }

  save(clinica: Clinica): Observable<Mensagem> {
    if (clinica.id) {
      return this.http.put<Mensagem>(this.API+"/"+`${clinica.id}`, clinica);
    } else {
      return this.http.post<Mensagem>(this.API, clinica);
    }
  }

  deletar(id: number): Observable<Mensagem> {
    return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
  }
}