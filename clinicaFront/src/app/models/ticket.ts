import { Calendario } from "./calendario";
import { Consulta } from "./consulta";
import { Doutor } from "./doutor";
import { Paciente } from "./paciente";

export class Ticket {
    id!: number;
    pacienteId!: Paciente;
    doutorId!: Doutor;
    data!: Calendario;
    consulta!: Consulta;
    logMarcar!: Date;
}
