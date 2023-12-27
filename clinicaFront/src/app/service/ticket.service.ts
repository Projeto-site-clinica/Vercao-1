import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from '../models/ticket';
import { Mensagem } from '../models/mensagem';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  API: string = 'http://localhost:8080/ticket';
  http = inject(HttpClient);

  constructor() { }

  listar(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.API}` + "/lista");
  }

  save(ticket: Ticket): Observable<Mensagem> {
    if (ticket.id) {
      return this.http.put<Mensagem>(this.API+"/"+`${ticket.id}`, ticket);
    } else {
      return this.http.post<Mensagem>(this.API, ticket);
    }
  }

  deletar(id: number): Observable<Mensagem> {
    return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
  }
}