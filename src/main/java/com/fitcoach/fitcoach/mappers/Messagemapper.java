package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Message;
import com.fitcoach.fitcoach.dtos.MessageDTO;

public interface Messagemapper {
    MessageDTO map(Message message);
    Message map(MessageDTO messageDTO);
}
