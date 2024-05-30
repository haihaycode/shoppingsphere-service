package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="order_status")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class OrderStatus implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ORDER_STATUS_ID")
    private Integer id;

    @Column(name="ORDER_STATUS_NAME")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="status")
    @JsonIgnore
    private List<Order> orders;
}
