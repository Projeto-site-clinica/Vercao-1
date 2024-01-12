import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Mensagem } from '../models/mensagem';
import { Doutor } from '../models/doutor';

@Injectable({
  providedIn: 'root'
})
export class DoutorService {
  API: string = 'http://localhost:8080/doutor';
  http = inject(HttpClient);

  constructor() { }

  buscarPorId(id: number): Observable<Doutor> {
    return this.http.get<Doutor>(this.API + "/" + `${id}`);
  }

  listar(): Observable<Doutor[]> {
    return this.http.get<Doutor[]>(`${this.API}` + "/lista");
  }

  save(doutor: Doutor): Observable<Mensagem> {
    if (doutor.id) {
      return this.http.put<Mensagem>(this.API+"/"+`${doutor.id}`, doutor);
    } else {
      return this.http.post<Mensagem>(this.API, doutor);
    }
  }

  deletar(id: number): Observable<Mensagem> {
    return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
  }
}