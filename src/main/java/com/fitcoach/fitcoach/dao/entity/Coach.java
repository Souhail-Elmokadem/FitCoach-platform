package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity

@Data
@Table(name = "coach")
public class Coach extends Person{


    @OneToMany(mappedBy = "coach")
    private List<Programme> programmes;

    @OneToMany(mappedBy = "coach")
    private Collection<Client> members;

    @OneToMany(mappedBy = "coach")
    private List<Review> comments;



    public Coach(){
        super();
        this.setRole(Role.COACH);
    }


}
