import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { DoutorHorario } from '../models/doutor-horario';

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
}
