package com.shoppingsphere.shoppingsphereservice.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
@Entity
@Table(name = "thumbnail")
public class Thumbnail implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    @Column(name = "path",columnDefinition="TEXT")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
