import { Program } from "./Program";

export interface Client{
    firstName:string;
    lastName:string;
    email:string;
    role:string;
    avatar:string;
    createdAt:string;
    programme:Program;
}