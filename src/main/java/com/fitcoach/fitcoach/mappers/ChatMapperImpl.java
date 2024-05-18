package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dtos.ChatDTO;
import org.springframework.beans.BeanUtils;

public class ChatMapperImpl implements ChatMapper{
    @Override
    public ChatDTO map(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();
        BeanUtils.copyProperties(chat,chatDTO);
        return chatDTO;
    }

    @Override
    public Chat map(ChatDTO chatDTO) {
        Chat chat = new Chat();
        BeanUtils.copyProperties(chatDTO,chat);
        return chat;

    }
}
