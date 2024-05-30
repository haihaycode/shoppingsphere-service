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

    @Length(min = 5, max = 45, message = "Tên phải trong khoảng từ 5 - 45 ký tự!")
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Length(min = 10, max = 255, message = "Vui lòng nhập vào địa chỉ nhận hàng hợp lệ!")
    @Column(name = "SHIPPING_ADDRESS", nullable = false)
    private String address;

    @Length(min = 9, max = 15, message = "Số điện thoại phải từ 9 đến 15 ký tự số!")
    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME", insertable = false, updatable = false)
    private Date updatedTime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

}
