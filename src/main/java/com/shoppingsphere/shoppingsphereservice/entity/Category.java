package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "products")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Integer id;

    @NotBlank(message = "Tên danh mục không được để trống!")
    @Column(name = "CATEGORY_NAME", length = 45, unique = true, nullable = false)
    private String name;


    @Column(name = "CATEGORY_DESCRIPTION", length = 45, unique = true, nullable = false)
    private String description;

    @Column(name = "LOGO", length = 255)
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(name = "ENABLED", length = 45)
    private Boolean enabled;

    @Transient
    private MultipartFile image;


    public Category(String name) {
        super();
        this.name = name;
    }
}
