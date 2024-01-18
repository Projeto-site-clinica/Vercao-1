import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultaCepService {
  API: string = 'https://viacep.com.br/ws/';
  http = inject(HttpClient);
  toastr = inject(ToastrService);

  constructor() { }

  consultaCEP(cep: string) {
    cep = cep.replace(/\D/g, '');

    if (cep !== '') {
      const validacep = /^[0-9]{8}$/;
    
      if (validacep.test(cep)) {
        return this.http.get(`${this.API}${cep}/json`);
      } else {
        this.toastr.error('O CEP deve conter exatamente 8 números!');
      }
    } else {
      this.toastr.error('O CEP é obrigatório!');
    }
    
    return new Observable;
  }
  
}
