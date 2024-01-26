import { Injectable, inject } from '@angular/core';
import { Paciente } from '../models/paciente';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Mensagem } from '../models/mensagem';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {
  API: string = 'http://localhost:8080/paciente';
  http = inject(HttpClient);

  constructor() { }

  buscarPorId(id: number): Observable<Paciente> {
    return this.http.get<Paciente>(this.API + "/" + `${id}`);
  }

  listar(): Observable<Paciente[]> {
    return this.http.get<Paciente[]>(`${this.API}` + "/lista");
  }

  save(paciente: Paciente): Observable<Mensagem> {
    if (paciente.id) {
      return this.http.put<Mensagem>(this.API+"/"+`${paciente.id}`, paciente);
    } else {
      return this.http.post<Mensagem>(this.API, paciente);
    }
  }
  editar(paciente: Paciente): Observable<Mensagem> {
    return this.http.put<Mensagem>(this.API+"/"+`${paciente.id}`, paciente);
  }

  deletar(id: number): Observable<Mensagem> {
    return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
  }
}