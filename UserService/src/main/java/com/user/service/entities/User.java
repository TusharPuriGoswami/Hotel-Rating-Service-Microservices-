package com.user.service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro-user")
public class User {

    @Id

    @Column(name = "Id")
    private String userId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "About")
    private String about;

    @Transient ///  This annotation means the data does not store in the database
    private List<Rating> rating = new ArrayList<>();







}
