import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Mensagem } from '../models/mensagem';
import { Consulta } from '../models/consulta';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  API: string = 'http://localhost:8080/consulta';
  http = inject(HttpClient);

  constructor() { }

  listar(): Observable<Consulta[]> {
    return this.http.get<Consulta[]>(`${this.API}` + "/lista");
  }

  save(consulta: Consulta): Observable<Mensagem> {
    if (consulta.id) {
      return this.http.put<Mensagem>(this.API+"/"+`${consulta.id}`, consulta);
    } else {
      return this.http.post<Mensagem>(this.API, consulta);
    }
  }

  deletar(id: number): Observable<Mensagem> {
    return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
  }
}