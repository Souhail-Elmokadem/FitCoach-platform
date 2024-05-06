package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@Table(name = "client")
public class Client extends Person {


    @ManyToOne
    private Coach coach;

    @ManyToOne
    private Programme programme;

    @OneToMany(mappedBy = "client")
    Collection<Paiement> paiementsHistory;

    @OneToMany(mappedBy = "client")
    Collection<Review> reviews;

    @Override
    public String toString() {

        return super.toString()+"Client{" +
                "coach=" + coach +
                ", programme=" + programme +
                ", paiementsHistory=" + paiementsHistory +
                ", reviews=" + reviews +
                '}';
    }

    public Client(){
        super();
        this.setRole(Role.USER);

    }

}
