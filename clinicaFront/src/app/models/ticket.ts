import { Consulta } from "./consulta";
import { Doutor } from "./doutor";
import { DoutorHorario } from "./doutor-horario";
import { Paciente } from "./paciente";

export class Ticket {
    id!: number;
    pacienteId!: Paciente;
    doutorId!: Doutor;
    horario!: DoutorHorario;
    consulta!: Consulta;
    logMarcar!: Date;
}
