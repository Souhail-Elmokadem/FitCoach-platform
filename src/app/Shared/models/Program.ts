import { Coach } from "./Coach";
export interface Program {
    id: number;

    nom: String;

    description: String;

    attachment: String;

    createdAt: Date;

    updateAt: Date;

    duree: number; // en semaines

    objectifs: String;

    coach: Coach;
}