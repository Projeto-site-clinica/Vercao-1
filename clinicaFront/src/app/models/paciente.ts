import { Abstract } from "./abstract";

export class Paciente extends Abstract{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
}