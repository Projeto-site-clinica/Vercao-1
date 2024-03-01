import { Doutor } from "./doutor";

export class Consulta {
    id!: number;
    nomeConsulta!: string;
    valor!: number;
    descricao!: string;
    duracao!: Date;
    doutorConsulta!: Doutor;
}