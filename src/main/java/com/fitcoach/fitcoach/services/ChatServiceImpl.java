package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.*;
import com.fitcoach.fitcoach.dao.entity.*;
import com.fitcoach.fitcoach.dtos.ChatDTO;
import com.fitcoach.fitcoach.dtos.MessageDTO;
import com.fitcoach.fitcoach.mappers.ChatMapper;
import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.CoachMapper;
import com.fitcoach.fitcoach.mappers.Messagemapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatManager {


    private ChatRepository chatRepository;
    private CoachRepository coachRepository;
    private ClientRepository clientRepository;



    private PersonRepository personRepository;
    private MessageRepository messageRepository;


    ChatMapper chatMapper;

    Messagemapper messagemapper;
    CoachMapper coachMapper;
    ClientMapper clientMapper;


    @Override
    public ChatDTO createChat(ChatDTO chatDTO) {
        return chatMapper.map(chatRepository.save(chatMapper.map(chatDTO)));
    }

    @Override
    public Collection<ChatDTO> getAllChats() {
        return chatRepository.findAll().stream().map(chatMapper::map).collect(Collectors.toList());
    }

    @Override
    public ChatDTO getChatById(int id) {
        return null;
    }

    @Override
    public MessageDTO addMessageToChat(Long chatId, String content,String senderemail,String replayemail) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        Message message = new Message(null,new Date(),content,senderemail,replayemail,chat);
        return messagemapper.map(messageRepository.save(message));
    }

    @Override
    public Collection<MessageDTO> getMessagesByChatId(Long chatId) {
        return null;
    }

    @Override
    public Collection<MessageDTO> getMessagesBySenderReply(String senderemail, String replayemail) {
        return null;
    }

    @Override
    public ChatDTO findByCoachAndClient(String senderemail, String replyemail) {
        Person p = personRepository.findByEmail(senderemail);
        Person p2 = personRepository.findByEmail(replyemail);
        Chat chat = null;
        if (p instanceof Coach){
            chat = chatRepository.findByCoachIdAndClientId(p.getId(), p2.getId());
        } else if (p instanceof Client) {
            chat = chatRepository.findByCoachIdAndClientId(p2.getId(), p.getId());
        }
        ChatDTO chatDTO = chatMapper.map(chat);
        chatDTO.setMessageDTOS(chat.getMessageList().stream().map(messagemapper::map).collect(Collectors.toList()));
        chatDTO.setCoach(coachMapper.coachToDTO(chat.getCoach()));
        chatDTO.setClient(clientMapper.map(chat.getClient()));
        return chatDTO;


//        return  new ChatDTO();
    }
}
