import { Component, inject } from '@angular/core';
import { Login } from '../../models/login';
import { LoginService } from '../../service/login.service';
import { PacienteService } from '../../service/paciente.service';
import { ToastrService } from 'ngx-toastr';
import { Paciente } from '../../models/paciente';
import { DoutorService } from '../../service/doutor.service';
import { ClinicaService } from '../../service/clinica.service';
import { SecretariaService } from '../../service/secretaria.service';
import { Doutor } from '../../models/doutor';
import { Clinica } from '../../models/clinica';
import { Secretaria } from '../../models/secretaria';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.scss'
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
