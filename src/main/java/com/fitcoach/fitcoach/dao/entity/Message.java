package com.fitcoach.fitcoach.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date time = new Date(System.currentTimeMillis());
    private String content;

    private String Senderemail;

    private String Replyemail;

    @ManyToOne
    private Chat chat;

}
