import { Component, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Doutor } from 'src/app/models/doutor';
import { DoutorHorario } from 'src/app/models/doutor-horario';
import { DoutorService } from 'src/app/service/doutor.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-horarios',
  templateUrl: './horarios.component.html',
  styleUrls: ['./horarios.component.scss']
})
export class HorariosComponent {

  loginService = inject(LoginService);
  doutorService = inject(DoutorService);
  toastr = inject(ToastrService);

  doutor: Doutor = new Doutor();

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
        console.log(`entrou`);
        this.doutor = objeito;

        let qtdDias = 0;
        if (this.doutor.horarios != null)
          qtdDias = this.doutor.horarios.length;
        else
          this.doutor.horarios = [];

        for (let i = 0; i < (7 - qtdDias); i++) {
          let horario = new DoutorHorario();
          horario.diaSemana = i + 1;
          this.doutor.horarios.push(horario);
        }


        console.log(this.doutor);
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
      }
    })
  }

  retornaDiaSemana(num: number) {
    if (num == 1)
      return "Dom";
    else if (num == 2)
      return "Seg";
    else if (num == 3)
      return "Ter";
    else if (num == 4)
      return "Qua";
    else if (num == 5)
      return "Qui";
    else if (num == 6)
      return "Sex";
    else if (num == 7)
      return "Sáb";
    else return null;
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
