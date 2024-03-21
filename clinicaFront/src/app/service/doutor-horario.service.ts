import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { DoutorHorario } from '../models/doutor-horario';
import { Mensagem } from '../models/mensagem';

@Injectable({
  providedIn: 'root'
})
export class DoutorHorarioService {
  API: string = 'http://localhost:8080/doutorHorario';
    http = inject(HttpClient);
  
    constructor() { }

    buscarPorId(id: number): Observable<DoutorHorario> {
      return this.http.get<DoutorHorario>(this.API + "/" + `${id}`);
    }
  
    listar(): Observable<DoutorHorario[]> {
      return this.http.get<DoutorHorario[]>(`${this.API}` + "/lista");
    }

    save(doutorHorario: DoutorHorario): Observable<Mensagem> {
      if (doutorHorario.id) {
        return this.http.put<Mensagem>(this.API+"/"+`${doutorHorario.id}`, doutorHorario);
      } else {
        return this.http.post<Mensagem>(this.API, doutorHorario);
      }
    }
  
    deletar(id: number): Observable<Mensagem> {
      return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
    }
}
