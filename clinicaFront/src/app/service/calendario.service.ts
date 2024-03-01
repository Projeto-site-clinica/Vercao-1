// import { HttpClient } from '@angular/common/http';
// import { Injectable, inject } from '@angular/core';
// import { Calendario } from '../models/calendario';
// import { Observable } from 'rxjs';
// import { Mensagem } from '../models/mensagem';

// @Injectable({
//   providedIn: 'root'
// })
// export class CalendarioService {
//   API: string = 'http://localhost:8080/calendario';
//   http = inject(HttpClient);

//   constructor() { }

//   listar(): Observable<Calendario[]> {
//     return this.http.get<Calendario[]>(`${this.API}` + "/lista");
//   }

//   save(calendario: Calendario): Observable<Mensagem> {
//     if (calendario.id) {
//       return this.http.put<Mensagem>(this.API+"/"+`${calendario.id}`, calendario);
//     } else {
//       return this.http.post<Mensagem>(this.API, calendario);
//     }
//   }

//   deletar(id: number): Observable<Mensagem> {
//     return this.http.delete<Mensagem>(this.API + "/" + `${id}`);
//   }
// }