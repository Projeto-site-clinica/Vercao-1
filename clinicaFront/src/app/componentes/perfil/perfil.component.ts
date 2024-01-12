import { Component, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Clinica } from 'src/app/models/clinica';
import { Doutor } from 'src/app/models/doutor';
import { Paciente } from 'src/app/models/paciente';
import { Secretaria } from 'src/app/models/secretaria';
import { ClinicaService } from 'src/app/service/clinica.service';
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
  loginService = inject(LoginService);
  pacienteService = inject(PacienteService);
  doutorService = inject(DoutorService);
  clinicaService = inject(ClinicaService);
  secretariaService = inject(SecretariaService);
  toastr = inject(ToastrService);

  paciente: Paciente = new Paciente();
  doutor: Doutor = new Doutor();
  clinica: Clinica = new Clinica();
  secretaria: Secretaria = new Secretaria();

  constructor() {
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

}
