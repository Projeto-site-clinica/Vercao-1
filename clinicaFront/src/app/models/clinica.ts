import { Abstract } from "./abstract";
import { Doutor } from "./doutor";
import { Secretaria } from "./secretaria";

export class Clinica extends Abstract{
    cnpj!: string;
    avaliacao!: number;
    descricao!: string;
    solicitacao: boolean = false;
    doutores!: Doutor[];
    secretarias!: Secretaria[];
}