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
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private Integer id;

    @NotBlank(message = "Tên thương hiệu không được để trống!")
    @Column(name = "BRAND_NAME", length = 45, unique = true, nullable = false)
    private String name;

    @Column(name = "BRAND_DESCRIPTION", length = 45, unique = true, nullable = false)
    private String description;

    @Column(name = "LOGO", length = 255)
    private String logo;

    @Column(name = "ENABLED", length = 45)
    private Boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @Transient
    private MultipartFile image;

    public Brand(String name) {
        super();
        this.name = name;
    }
}
