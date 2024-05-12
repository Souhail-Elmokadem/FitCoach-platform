package com.fitcoach.fitcoach.dtos;


import com.fitcoach.fitcoach.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class CoachDTO{

    private Long id;

    private String firstName;

    private String lastName;
    private String email;

    private String avatar;

    private Date createdAt;

    private Date updateAt;


}
