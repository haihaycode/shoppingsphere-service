package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "ORDER_STATUS_ID")
    private OrderStatus status;



    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME", insertable = false, updatable = false)
    private Date updatedTime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

}
