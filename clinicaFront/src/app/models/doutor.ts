import { Usuario } from "./usuario";
import { Clinica } from "./clinica";
import { Consulta } from "./consulta";
import { DoutorHorario } from "./doutor-horario";

export class Doutor extends Usuario{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
    formacao!: string;
    avaliacao!: number;
    descricao!: string;
    solicitacao: boolean = false;
    clinica!: Clinica;
    consultas!: Consulta[];
    horarios: DoutorHorario[] = [];
}