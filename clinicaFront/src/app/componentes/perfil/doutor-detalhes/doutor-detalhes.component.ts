import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Consulta } from 'src/app/models/consulta';
import { Doutor } from 'src/app/models/doutor';
import { Mensagem } from 'src/app/models/mensagem';
import { DoutorService } from 'src/app/service/doutor.service';

@Component({
  selector: 'app-doutor-detalhes',
  templateUrl: './doutor-detalhes.component.html',
  styleUrls: ['./doutor-detalhes.component.scss']
})
export class DoutorDetalhesComponent {
  @Input() doutor: Doutor = new Doutor();
  @Input() consulta: Consulta = new Consulta();
  @Output() retorno = new EventEmitter<Mensagem>;

  doutorService = inject(DoutorService);
  toastr = inject(ToastrService);

  salvar(formulario: any) {
    if (!formulario.valid){
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    }else{
      this.doutor.consulta.push({ ...this.doutor.consulta, ...formulario.value });
      this.doutorService.save(this.doutor).subscribe({
        next: mensagem => {
          this.toastr.success("Consulta adicionada com Sucesso!");
          this.retorno.emit(mensagem);
        },
        error: erro => {
          this.toastr.error(erro.error.mensagem);
        }
      });
    }
  }
}
