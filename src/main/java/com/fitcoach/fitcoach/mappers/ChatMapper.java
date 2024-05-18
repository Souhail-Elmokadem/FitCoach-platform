package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dtos.ChatDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ChatMapper {
    ChatDTO map(Chat chat);
    Chat map(ChatDTO chatDTO);
}
