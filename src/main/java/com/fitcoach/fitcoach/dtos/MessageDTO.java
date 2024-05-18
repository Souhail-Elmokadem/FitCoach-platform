package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private Date time;
    private String content;


    private String Senderemail;

    private String Replyemail;


}
