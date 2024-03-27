import { Time } from "@angular/common";

export class DoutorHorario {
    id!: number;
    diaSemana!: number;
    horarioInicialManha!: Date;
    horarioFinalManha!: Date;
    horarioInicialTarde!: Date;
    horarioFinalTarde!: Date;
}
