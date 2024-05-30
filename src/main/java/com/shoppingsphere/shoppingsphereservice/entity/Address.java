package com.shoppingsphere.shoppingsphereservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
@Entity
@Table(name = "address")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 5, max = 45, message = "Tên phải trong khoảng từ 5 - 45 ký tự!")
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Length(min = 10, max = 255, message = "Vui lòng nhập vào địa chỉ nhận hàng hợp lệ!")
    @Column(name = "SHIPPING_ADDRESS", nullable = false)
    private String address;

    @Length(min = 9, max = 15, message = "Số điện thoại phải từ 9 đến 15 ký tự số!")
    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
