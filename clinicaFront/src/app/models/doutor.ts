import { Usuario } from "./usuario";
import { Clinica } from "./clinica";
import { Consulta } from "./consulta";

export class Doutor extends Usuario{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
    formacao!: string;
    avaliacao!: number;
    descricao!: string;
    solicitacao: boolean = false;
    clinica!: Clinica;
    consulta!: Consulta[];
    horarioStart!: Date;
    horarioEnd!: Date;
    intervaloStart!: Date;
    intervaloEnd!: Date;
}