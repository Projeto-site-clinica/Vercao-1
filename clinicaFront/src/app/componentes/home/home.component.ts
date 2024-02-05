import { Component, EventEmitter, Output, inject } from '@angular/core';
import { NgbDateStruct, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Doutor } from 'src/app/models/doutor';
import { DoutorService } from 'src/app/service/doutor.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  @Output() retorno = new EventEmitter<any>();

  listaDoutoresFiltrada: Doutor[] = [];
  listaDoutorseOrginal: Doutor[] = [];
  doutorSelecionado: Doutor = new Doutor();
  termoPesquisa!: "";
  modalRef!: NgbModalRef;
  indiceSelecionadoParaEdicao!: number;

  doutorService = inject(DoutorService);
  modalService = inject(NgbModal);

  model!: NgbDateStruct;
	date!: { year: number; month: number };
  
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

  mostrarDetalhes(doutor: Doutor, modal: any, indice: number) {
    this.doutorSelecionado = Object.assign({}, doutor);
    this.indiceSelecionadoParaEdicao = indice;

    this.modalRef = this.modalService.open(modal, { size: 'md' });
  }

  @Output() realizarPesquisa(termoPesquisa: string) {
    termoPesquisa.toLowerCase();
    if (!termoPesquisa) {
      this.listaDoutoresFiltrada = this.listaDoutorseOrginal;
    } else {
      this.listaDoutoresFiltrada = this.listaDoutorseOrginal.filter((doutor: Doutor) => {
        const nome = doutor.nome.toLowerCase();
        const formacao = doutor.formacao.toLowerCase();
        return (
          nome.includes(termoPesquisa) ||
          formacao.includes(termoPesquisa)
        );
      });
    }
  }

  
}
