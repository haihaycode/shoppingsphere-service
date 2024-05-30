package com.shoppingsphere.shoppingsphereservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "products")
@Entity
@Table(name = "sizes")
public class Size implements Serializable {
    @Id
    @Column(name = "SIZE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Create Many-To-One relationship with Size
    @Column(name = "size_value")
    private String value; // size value (S, M, L, XL...)

}
