import { Component, EventEmitter, Output, inject } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Consulta } from 'src/app/models/consulta';
import { Doutor } from 'src/app/models/doutor';
import { Mensagem } from 'src/app/models/mensagem';
import { DoutorService } from 'src/app/service/doutor.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-servicios',
  templateUrl: './servicios.component.html',
  styleUrls: ['./servicios.component.scss']
})
export class ServiciosComponent {
  @Output() retorno = new EventEmitter<any>();

  loginService = inject(LoginService);
  doutorService = inject(DoutorService);
  toastr = inject(ToastrService);
  modalService = inject(NgbModal);

  modalRef!: NgbModalRef;
  doutor: Doutor = new Doutor();

  indiceSelecionadoParaEdicao!: number;
  tituloModal!: string;
  consultaParaEditar: Consulta = new Consulta();

  constructor() {
    let aux = this.loginService.getUser().id;
    let id = 0;
    if (aux != null)
      id = +aux;

    if (this.loginService.getUser().role == "DOUTOR")
      this.getDoutor(id);
  }

  getDoutor(id: number) {
    this.doutorService.buscarPorId(id).subscribe({
      next: objeito => {
        this.doutor = objeito;
        console.log(this.doutor);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
      }
    })
  }

  adicionarConsulta(modalDoutor: any, doutor: Doutor) {
    this.consultaParaEditar = new Consulta();
    this.modalService.open(modalDoutor, { size: 'lg', scrollable: true });

    this.tituloModal = "Adicionar Consulta";
    console.log(this.doutor);
  }

  atualizarLista(mensagem: Mensagem) {
    this.modalService.dismissAll();

    let aux = this.loginService.getUser().id;
    let id = 0;
    if (aux != null)
      id = +aux;

    if (this.loginService.getUser().role == "DOUTOR")
      this.getDoutor(id);

    this.retorno.emit("ok");
  }

  salvarDoutor() {
    console.log(this.doutor);
    this.doutorService.save(this.doutor).subscribe({
      next: mensagem => {
        this.toastr.success(mensagem.mensagem);
        console.log(mensagem);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
        console.error(erro);
      }
    });
  }
}
