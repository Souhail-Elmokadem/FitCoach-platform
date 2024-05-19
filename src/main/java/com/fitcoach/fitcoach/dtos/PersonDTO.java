package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
    private String profile;
    private String avatar;

    private Date createdAt;



    private Date updateAt;

//    private String password;
    private Role role;
}
