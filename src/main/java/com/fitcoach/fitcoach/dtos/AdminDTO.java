package com.fitcoach.fitcoach.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String avatar;

    private Date createdAt;

    private Date updateAt;

}
