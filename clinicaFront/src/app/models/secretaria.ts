import { Usuario } from "./usuario";
import { Clinica } from "./clinica";

export class Secretaria extends Usuario{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
    clinicaId!: Clinica;
}