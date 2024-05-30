package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "orderLines", "imageFile" })
@ToString(exclude = { "orderLines", "imageFile" })
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer id;

    @Length(min = 1, max = 255, message = "Tên phải nằm trong khoảng từ 1 đến 255 ký tự!")
    @Column(name = "PRODUCT_NAME", unique = true, length = 255)
    private String name;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CATEGORY_ID")
    public Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
                name = "PRODUCT_COLOR",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COLOR_ID")
    )
    private List<Color> colors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCT_SIZE",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SIZE_ID")
    )
    private List<Size> sizes;


    @Column(name = "DISCOUNT_PERCENT")
    @Range(min = 0, max = 100, message = "Phần trăm giảm giá phải nằm trong khoảng từ 0 đến 100")
    private Integer discountPercent;

    @NotNull(message = "Vui lòng nhập vào giá sản phẩm")
    @Range(min = 0, max = 100000000, message = "Giá trong khoảng từ 0 - 100 triệu")
    @Column(name = "PRICE")
    private Double price ;



    @Length(max = 45, message = "Đơn vị tính phải nhỏ hơn 45 ký tự!")
    @Column(name = "PRODUCT_UNIT", length = 45)
    private String unit;

    @NotNull(message = "Vui lòng nhập vào số lượng sản phẩm")
    @Range(min = 0, max = 5000, message = "Số lượng trong khoảng từ 0 - 5000")
    @Column(name = "QUANTITY_IN_STOCK")
    private Integer qtyInStock;

    @Length(min = 0, max = 255, message = "Mô tả phải nhỏ hơn 256 ký tự")
    @Column(name = "SHORT_DESCRIPTION", length = 255)
    private String shortDescription;

    @Column(name = "DESCRIPTION", columnDefinition="TEXT")
    private String description;


    @Column(name = "THUMBNAIL", columnDefinition="TEXT")
    private String thumbnail;



    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Thumbnail> thumbnails ;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "VIEW", insertable = false)
    private Integer view;
    @Length(min = 0, max = 255, message = "Bảo hành phải nhỏ hơn 46 ký tự")
    @Column(name = "WARRANTY", length = 45)
    private String warranty;

    @Column(name = "CREATED_TIME", insertable = false, updatable = false)
    private Date createdTime;



    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    @Transient
    private MultipartFile imageFile;
    public Double getDiscountedPrice() {
        return this.price * (100 - this.discountPercent) / 100;
    }
}
