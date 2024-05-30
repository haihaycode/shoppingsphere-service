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
@Table(name = "colors")
public class Color implements Serializable {
    @Id
    @Column(name = "COLOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "color_value")
    private String value; // color value (Red, Blue, Green...)
    @Column(name = "color_code")
    private String code; // color value (Red, Blue, Green...)
}
