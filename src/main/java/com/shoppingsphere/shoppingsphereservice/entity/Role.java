package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Integer id;

    @NotNull
    @Column(name = "ROLE_NAME", unique=true, nullable=false, length=45)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<User> users;
}
