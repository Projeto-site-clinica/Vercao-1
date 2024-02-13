import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Doutor } from 'src/app/models/doutor';
import { Mensagem } from 'src/app/models/mensagem';
import { Ticket } from 'src/app/models/ticket';

@Component({
  selector: 'app-doutor-perfil',
  templateUrl: './doutor-perfil.component.html',
  styleUrls: ['./doutor-perfil.component.scss']
})
export class DoutorPerfilComponent {
  @Input() doutor:Doutor = new Doutor();
  @Output() retorno = new EventEmitter<any>();

  ticket:Ticket = new Ticket();
  doutor2:Doutor = new Doutor();
  modalService = inject(NgbModal);
  
  atualizarLista(mensagem: Mensagem) {
    this.modalService.dismissAll();
    this.retorno.emit("ok");
  }

  cadastrarTicket(modalSabor : any, doutor: Doutor){
    doutor = doutor;
    this.ticket = new Ticket();
    this.modalService.open(modalSabor, { size: 'md' });
  }
}
