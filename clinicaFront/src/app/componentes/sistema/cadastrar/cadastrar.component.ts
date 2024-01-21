import { Component, Input, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Clinica } from 'src/app/models/clinica';
import { Doutor } from 'src/app/models/doutor';
import { Paciente } from 'src/app/models/paciente';
import { Secretaria } from 'src/app/models/secretaria';
import { ClinicaService } from 'src/app/service/clinica.service';
import { DoutorService } from 'src/app/service/doutor.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { SecretariaService } from 'src/app/service/secretaria.service';
import { ConsultaCepService } from 'src/app/service/consulta-cep.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.scss']
})
export class CadastrarComponent {
  @Input() paciente: Paciente = new Paciente();
  @Input() doutor: Doutor = new Doutor();
  @Input() clinica: Clinica = new Clinica();
  @Input() secretaria: Secretaria = new Secretaria();

  pacienteService = inject(PacienteService);
  doutorService = inject(DoutorService);
  clinicaService = inject(ClinicaService);
  secretariaService = inject(SecretariaService);
  toastr = inject(ToastrService);
  cepService = inject(ConsultaCepService);
  roteador = inject(Router);

  tipoUsuario!: any;

  constructor() {

  }

  salvarPaciente(formPaciente: any) {
    if (!formPaciente.valid) {
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    } else {
      this.pacienteService.save(this.paciente).subscribe({
        next: mensagem => {
          this.toastr.success(mensagem.mensagem);
          this.roteador.navigate(['login']);
        },
        error: erro => {
          this.toastr.error(erro.error.mensagem);
        }
      });
    }
  }

  salvarDoutor(formDoutor: any) {
    if (!formDoutor.valid) {
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    } else {
      this.doutorService.save(this.doutor).subscribe({
        next: mensagem => {
          this.toastr.success(mensagem.mensagem);
          this.roteador.navigate(['login']);
        },
        error: erro => {
          this.toastr.error(erro.error.mensagem);
        }
      });
    }
  }

  salvarClinica(formClinica: any) {
    if (!formClinica.valid) {
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    } else {
      this.clinicaService.save(this.clinica).subscribe({
        next: mensagem => {
          this.toastr.success(mensagem.mensagem);
          this.roteador.navigate(['login']);
        },
        error: erro => {
          this.toastr.error(erro.error.mensagem);
        }
      });
    }
  }

  salvarSecretaria(formSecretaria: any) {
    if (!formSecretaria.valid) {
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    } else {
      this.secretariaService.save(this.secretaria).subscribe({
        next: mensagem => {
          this.toastr.success(mensagem.mensagem);
          this.roteador.navigate(['login']);
        },
        error: erro => {
          this.toastr.error(erro.error.mensagem);
        }
      });
    }
  }

  consultaCep(event: any, form: NgForm) {
    const cep = event.target.value;
    if (cep !== '') {
      this.cepService.consultaCEP(cep).subscribe({
        next: mensagem => {
          this.populaDadosForm(mensagem, form);
        },
        error: erro => {
          this.toastr.error('Ocorreu um erro ao consultar o CEP.');
        }
      });
    }
  }

  populaDadosForm(dados: any, form: NgForm) {
    if (dados && !dados.erro) {
      form.form.patchValue({
        rua: dados.logradouro,
        cep: dados.cep,
        complemento: dados.complemento,
        bairro: dados.bairro,
        cidade: dados.localidade,
        estado: dados.uf
      });
      return true;
    } else {
      this.toastr.error('CEP inválido por favor preencha um valido!!');
      return false;
    }
  }
}
