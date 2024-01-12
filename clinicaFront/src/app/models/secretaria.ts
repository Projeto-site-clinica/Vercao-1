import { Clinica } from "./clinica";
import { Usuario } from "./usuario";

export class Secretaria extends Usuario{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
    clinicaId!: Clinica;
}
