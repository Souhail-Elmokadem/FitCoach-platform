package com.fitcoach.fitcoach.web;

import com.fitcoach.fitcoach.dao.entity.Message;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.services.ChatManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



import com.fitcoach.fitcoach.dtos.ChatDTO;
import com.fitcoach.fitcoach.dtos.MessageDTO;
import com.fitcoach.fitcoach.services.ChatManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatControllerApi {

    private ChatManager chatManager;



    @PostMapping
    public ResponseEntity<ChatDTO> createChat(@RequestBody ChatDTO chatDTO) {
        ChatDTO createdChat = chatManager.createChat(chatDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChat);
    }

    @GetMapping
    public ResponseEntity<Collection<ChatDTO>> getAllChats() {
        Collection<ChatDTO> chats = chatManager.getAllChats();
        return ResponseEntity.ok(chats);
    }

//    @PostMapping("/{chatId}")
//    public ResponseEntity<MessageDTO> addMessageToChat(@PathVariable Long chatId, @RequestBody MessageDTO messageDTO) {
//        //MessageDTO addedMessage = chatManager.addMessageToChat(chatId, messageDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(addedMessage);
//    }
    @PostMapping("/message")
    public MessageDTO addMessageToChatByCoachClient(@RequestParam("sender") String senderemail,
                                                                    @RequestParam("replay") String replayemail,
                                                                    @RequestParam("content") String content) {

        ChatDTO chatDTO = chatManager.findByCoachAndClient(replayemail,senderemail);
        //System.out.println(coachemail +' ' + clientemail);

        MessageDTO addedMessage = chatManager.addMessageToChat(chatDTO.getId(),content,senderemail,replayemail);
        return addedMessage;

//    return new MessageDTO();
    }

    @GetMapping("/message")
    public ChatDTO getChat(@RequestParam("sender") String senderemail,
                           @RequestParam("replay") String replayemail) {
        ChatDTO chatDTO = chatManager.findByCoachAndClient(replayemail,senderemail);
        return chatDTO;
    }





    //  other endpoints will added Coming Soon

}
