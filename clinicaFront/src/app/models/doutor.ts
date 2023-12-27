import { Abstract } from "./abstract";
import { Clinica } from "./clinica";
import { Consulta } from "./consulta";

export class Doutor extends Abstract{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
    formacao!: string;
    avaliacao!: number;
    descricao!: string;
    solicitacao: boolean = false;
    clinicaId!: Clinica;
    consulta!: Consulta[];
    horarioStart!: Date;
    horarioEnd!: Date;
}