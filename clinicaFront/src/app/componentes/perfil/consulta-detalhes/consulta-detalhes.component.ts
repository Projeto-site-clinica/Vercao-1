import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Consulta } from 'src/app/models/consulta';
import { Doutor } from 'src/app/models/doutor';
import { Mensagem } from 'src/app/models/mensagem';
import { Usuario } from 'src/app/models/usuario';
import { ConsultaService } from 'src/app/service/consulta.service';
import { DoutorService } from 'src/app/service/doutor.service';

@Component({
  selector: 'app-consulta-detalhes',
  templateUrl: './consulta-detalhes.component.html',
  styleUrls: ['./consulta-detalhes.component.scss']
})
export class ConsultaDetalhesComponent {
  @Input() doutor: Doutor = new Doutor();
  @Input() consulta: Consulta = new Consulta();
  @Output() retorno = new EventEmitter<Mensagem>;

  doutorService = inject(DoutorService);
  consultaService = inject(ConsultaService);
  toastr = inject(ToastrService);

  salvar(formulario: any) {
    console.log(this.doutor);
    if (!formulario.valid){
      this.toastr.error('Formulário inválido. Preencha os campos corretamente');
    }else{
      console.log(this.consulta);
      console.log(this.doutor);
      this.consulta.doutorConsulta = this.doutor;
      this.consultaService.save(this.consulta).subscribe({
        next: mensagem => {
          console.log(this.doutor);
          console.log(this.consulta);
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
