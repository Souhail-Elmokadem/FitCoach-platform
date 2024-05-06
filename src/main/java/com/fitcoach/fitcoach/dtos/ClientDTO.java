package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String avatar;

    private Date createdAt;
    private Role role;

    private Date updateAt;

    private CoachDTO coach;

    private Programme programme;


}
