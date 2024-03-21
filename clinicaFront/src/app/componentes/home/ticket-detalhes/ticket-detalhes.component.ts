import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Doutor } from 'src/app/models/doutor';
import { Mensagem } from 'src/app/models/mensagem';
import { Paciente } from 'src/app/models/paciente';
import { Ticket } from 'src/app/models/ticket';
import { LoginService } from 'src/app/service/login.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { TicketService } from 'src/app/service/ticket.service';

@Component({
  selector: 'app-ticket-detalhes',
  templateUrl: './ticket-detalhes.component.html',
  styleUrls: ['./ticket-detalhes.component.scss']
})
export class TicketDetalhesComponent {
  @Input() doutor:Doutor = new Doutor();
  @Input() ticket:Ticket = new Ticket();
  @Output() retorno = new EventEmitter<Mensagem>;

  
  paciente: Paciente = new Paciente();

  loginService = inject(LoginService);
  pacienteService = inject(PacienteService);
  ticketService = inject(TicketService);
  toastr = inject(ToastrService);

  constructor(){
    let aux = this.loginService.getUser().id;
    let id = 0;
    if (aux != null)
      id = +aux;

    if (this.loginService.getUser().role == "PACIENTE")
      this.getPaciente(id);
  }

  getPaciente(id: number) {
    this.pacienteService.buscarPorId(id).subscribe({
      next: objeito => {
        this.paciente = objeito;
      },
      error: erro => {
        this.toastr.error(erro.error.mensagem);
      }
    })
  }

  salvar(formulario: any) {
    if (!formulario.valid){
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    }else{
      console.log(this.doutor);
      console.log(this.paciente);

      

      this.ticketService.save(this.ticket).subscribe({
        next: (mensagem) => {
          this.ticket.doutorId = this.doutor;
      this.ticket.pacienteId = this.paciente;
          this.toastr.success(mensagem.mensagem);
          this.retorno.emit(mensagem);
        },
        error: erro => {
          console.error(erro);
          this.toastr.error(erro.erro.mensagem);
          
        }
      });
    }
  }

  byId(item1: any, item2: any) {
    if (item1 != null && item2 != null)
      return item1.id === item2.id;
    else
      return item1 === item2;
  }
}
