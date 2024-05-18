package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ChatRepository;
import com.fitcoach.fitcoach.dao.Repository.MessageRepository;
import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dao.entity.Message;
import com.fitcoach.fitcoach.dtos.ChatDTO;
import com.fitcoach.fitcoach.dtos.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface ChatManager {

    ChatDTO createChat(ChatDTO chat);

    Collection<ChatDTO> getAllChats();

    ChatDTO getChatById(int id);

    MessageDTO addMessageToChat(Long chatId, String content,String senderemail,String replayemail);

    Collection<MessageDTO> getMessagesByChatId(Long chatId);
    Collection<MessageDTO> getMessagesBySenderReply(String senderemail,String replayemail);

    ChatDTO findByCoachAndClient(String senderemail,String replyemail);

}
