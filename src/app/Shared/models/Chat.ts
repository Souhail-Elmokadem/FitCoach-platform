import { Client } from "./Client";
import { Coach } from "./Coach";
import { Message } from "./Message";

export interface Chat{
    chatId: Number;
    client: Client;
    coach: Coach;
    messageDTOS:Message[];
}
