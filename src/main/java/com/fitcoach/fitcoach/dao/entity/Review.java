package com.fitcoach.fitcoach.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;

    private String description;

    private Date createdAt;

    private Date updateAt;

    @ManyToOne
    private Coach coach;

    private String date;
    @Min(1)
    @Max(5)
    private Integer stars;

}
