package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

@Table(name = "coach")
public class Coach extends Person{



    @OneToMany(mappedBy = "coach")
    private List<Programme> programmes;

    @OneToMany(mappedBy = "coach")
    private Collection<Client> members;

    @OneToMany(mappedBy = "coach")
    private List<Review> comments;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private Collection<Chat> chats;




    public Coach() {

    }

    public Coach(Long id, String firstName, String lastName, String email,String profile, String avatar, Date date, Date date1, String password, Role role) {
        super(id,firstName,lastName,email,profile,avatar,date,date1,password,role);
        this.members=new ArrayList<>();
        this.comments = new ArrayList<>();
        this.programmes= new ArrayList<>();
        this.setAvatar("http://localhost:9090/content/logo.png");
    }
}
