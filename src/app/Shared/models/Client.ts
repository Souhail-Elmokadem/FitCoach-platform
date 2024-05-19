import { Program } from "./Program";

export interface Client{
    id:number;
    firstName:string;
    lastName:string;
    email:string;
    role:string;
    avatar:string;
    createdAt:Date;
    programme:Program;
}