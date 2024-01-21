import { Component, Output, inject } from '@angular/core';
import { Doutor } from 'src/app/models/doutor';
import { DoutorService } from 'src/app/service/doutor.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  listaDoutoresFiltrada: Doutor[] = [];
  listaDoutorseOrginal: Doutor[] = [];
  doutorSelecionado: Doutor = new Doutor();

  doutorService = inject(DoutorService);
  
  constructor(){
    this.listaDoutores();
  }

  listaDoutores(){
    this.doutorService.listar().subscribe({
      next: listarDoutores => {
        this.listaDoutoresFiltrada = listarDoutores;
        this.listaDoutorseOrginal = listarDoutores;
      }
    })
  }

  mostrarDetalhes(doutor: Doutor) {
    this.doutorSelecionado = doutor;

  }

  @Output() realizarPesquisa(termoPesquisa: string) {
    termoPesquisa.toLowerCase();
    if (!termoPesquisa) {
      this.listaDoutoresFiltrada = this.listaDoutorseOrginal;
    } else {
      this.listaDoutoresFiltrada = this.listaDoutorseOrginal.filter((doutor: Doutor) => {
        const nome = doutor.nome.toLowerCase();
        return (
          nome.includes(termoPesquisa)
        );
      });
    }
  }
}
