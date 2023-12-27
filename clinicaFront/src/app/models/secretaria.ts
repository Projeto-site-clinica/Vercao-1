import { Abstract } from "./abstract";
import { Clinica } from "./clinica";

export class Secretaria extends Abstract{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
    clinicaId!: Clinica;
}