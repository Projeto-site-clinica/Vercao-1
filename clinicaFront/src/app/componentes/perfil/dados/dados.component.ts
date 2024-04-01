import { Component, EventEmitter, Output, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Clinica } from 'src/app/models/clinica';
import { Doutor } from 'src/app/models/doutor';
import { Paciente } from 'src/app/models/paciente';
import { Secretaria } from 'src/app/models/secretaria';
import { ClinicaService } from 'src/app/service/clinica.service';
import { ConsultaCepService } from 'src/app/service/consulta-cep.service';
import { DoutorService } from 'src/app/service/doutor.service';
import { LoginService } from 'src/app/service/login.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { SecretariaService } from 'src/app/service/secretaria.service';

@Component({
  selector: 'app-dados',
  templateUrl: './dados.component.html',
  styleUrls: ['./dados.component.scss']
})
export class DadosComponent {
  loginService = inject(LoginService);
  pacienteService = inject(PacienteService);
  doutorService = inject(DoutorService);
  clinicaService = inject(ClinicaService);
  secretariaService = inject(SecretariaService);
  toastr = inject(ToastrService);
  cepService = inject(ConsultaCepService);

  paciente: Paciente = new Paciente();
  doutor: Doutor = new Doutor();
  clinica: Clinica = new Clinica();
  secretaria: Secretaria = new Secretaria();

  modoEdicao: boolean[] = [];
  editar = false;

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
