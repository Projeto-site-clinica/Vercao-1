import { Usuario } from "./usuario";

export class Paciente extends Usuario{
    rg!: string;
    cpf!: string;
    dataNacimento!: string;
}
