import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Clinica } from 'src/app/models/clinica';
import { Consulta } from 'src/app/models/consulta';
import { Doutor } from 'src/app/models/doutor';
import { Mensagem } from 'src/app/models/mensagem';
import { Paciente } from 'src/app/models/paciente';
import { Secretaria } from 'src/app/models/secretaria';
import { ClinicaService } from 'src/app/service/clinica.service';
import { ConsultaCepService } from 'src/app/service/consulta-cep.service';
import { DoutorService } from 'src/app/service/doutor.service';
import { LoginService } from 'src/app/service/login.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { SecretariaService } from 'src/app/service/secretaria.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent {
  @Output() retorno = new EventEmitter<any>();

  loginService = inject(LoginService);
  pacienteService = inject(PacienteService);
  doutorService = inject(DoutorService);
  clinicaService = inject(ClinicaService);
  secretariaService = inject(SecretariaService);
  toastr = inject(ToastrService);
  cepService = inject(ConsultaCepService);
  modalService = inject(NgbModal);

  modalRef!: NgbModalRef;
  paciente: Paciente = new Paciente();
  doutor: Doutor = new Doutor();
  clinica: Clinica = new Clinica();
  secretaria: Secretaria = new Secretaria();

  modoEdicao: boolean[] = [];
  editar = false;
  indiceSelecionadoParaEdicao!: number;
  tituloModal!: string;
  consultaParaEditar: Consulta = new Consulta();

  constructor() {
    for (let i = 0; i < 100; i++) {
      this.modoEdicao.push(false);
    }
    let aux = this.loginService.getUser().id;
    let id = 0;
    if (aux != null)
      id = +aux;

    if (this.loginService.getUser().role == "PACIENTE")
      this.getPaciente(id);
    else if (this.loginService.getUser().role == "DOUTOR")
      this.getDoutor(id);
    else if (this.loginService.getUser().role == "CLININCA")
      this.getClinica(id);
    else if (this.loginService.getUser().role == "SECRETARIA")
      this.getSecretaria(id);


  }

  getPaciente(id: number) {
    this.pacienteService.buscarPorId(id).subscribe({
      next: objeito => {
        this.paciente = objeito;
        console.log(this.paciente);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
      }
    })
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

  getClinica(id: number) {
    this.clinicaService.buscarPorId(id).subscribe({
      next: objeito => {
        this.clinica = objeito;
        console.log(this.clinica);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
      }
    })
  }

  getSecretaria(id: number) {
    this.secretariaService.buscarPorId(id).subscribe({
      next: objeito => {
        this.secretaria = objeito;
        console.log(this.secretaria);
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
    this.loginService.getUser().role == "DOUTOR"
    this.retorno.emit("ok");
  }

  salvarPaciente(posicao: number) {
    console.log(this.paciente);
    this.pacienteService.editar(this.paciente).subscribe({
      next: mensagem => {
        this.toastr.success(mensagem.mensagem);
        this.modoEdicao[posicao] = false;
        console.log(mensagem);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
        console.error(erro);
      }
    });
  }

  // consultaCep(event: any, form: NgForm) {
  //   const cep = event.target.value;
  //   if (cep !== '') {
  //     this.cepService.consultaCEP(cep).subscribe({
  //       next: mensagem => {
  //         this.populaDadosForm(mensagem, form);
  //       },
  //       error: erro => {
  //         this.toastr.error('Ocorreu um erro ao consultar o CEP.');
  //       }
  //     });
  //   }
  // }

  // populaDadosForm(dados: any, form: NgForm) {
  //   if (dados && !dados.erro) {
  //     form.form.patchValue({
  //       rua: dados.logradouro,
  //       cep: dados.cep,
  //       complemento: dados.complemento,
  //       bairro: dados.bairro,
  //       cidade: dados.localidade,
  //       estado: dados.uf
  //     });
  //     return true;
  //   } else {
  //     this.toastr.error('CEP inválido por favor preencha um valido!!');
  //     return false;
  //   }
  // }

}
