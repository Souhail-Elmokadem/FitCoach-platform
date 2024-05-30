package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)

public class Admin extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Admin() {
        super();
        this.setRole(Role.ADMIN);

    }

}
