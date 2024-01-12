import { Usuario } from "./usuario";
import { Doutor } from "./doutor";
import { Secretaria } from "./secretaria";

export class Clinica extends Usuario{
    cnpj!: string;
    avaliacao!: number;
    descricao!: string;
    solicitacao: boolean = false;
    doutores!: Doutor[];
    secretarias!: Secretaria[];
}