package com.fitcoach.fitcoach.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        private Client client;

        @ManyToOne
        private Coach coach;

        @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
        private Collection<Message> messageList;
}
