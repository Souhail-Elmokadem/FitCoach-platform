package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Message;
import com.fitcoach.fitcoach.dtos.MessageDTO;
import org.springframework.beans.BeanUtils;

public class MessageMapperImpl implements Messagemapper{
    @Override
    public MessageDTO map(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(message,messageDTO);
        return messageDTO;
    }

    @Override
    public Message map(MessageDTO messageDTO) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDTO,message);
        return message;
    }
}
