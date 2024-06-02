package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shoppingsphere.shoppingsphereservice.auth.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude= {"orders","roles"})//tạo func toString (exclude : loại bỏ các fields không muốn)
@EqualsAndHashCode(exclude= {"orders","roles"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    @Column(name = "FULL_NAME", length = 45)
    private String fullname;

    @Column(name = "USERNAME", length = 45, unique = true)
    private String username;



    @Column(name = "PASSWORD", length = 128)
    private String password;

    @Column(name = "EMAIL", length = 45, unique = true)
    private String email;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "ENABLED", length = 45)
    private Boolean enabled;

    @Column(name = "GENDER")
    private Boolean gender;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME", insertable = false, updatable = false)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME", insertable = false, updatable = false)
    private Date updatedTime;

    @Nationalized
    @Column(name = "AVATAR", length = 1000)
    private String avatar;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonIgnore
    private List<Order> orders;

    @Column(name = "resetPasswordToken")
    private String resetPasswordToken;

    @Column(name = "resetPasswordTokenExpiryDate")
    private Date resetPasswordTokenExpiryDate;

    @Column(name = "accessToken")
    private String accessToken;


     /*
    |||||| Method ||||||||
     */


    public String getAvatar() {
        return avatar == null ? "/public/user/images/avatar/default-avatar.png" : avatar;
    }
    public void generateResetPasswordToken() {
        this.resetPasswordToken = java.util.UUID.randomUUID().toString();
        this.resetPasswordTokenExpiryDate = new Date(System.currentTimeMillis() + 1000 * 60 *15);
    }

    public void clearResetPasswordToken() {
        this.resetPasswordToken = null;
        this.resetPasswordTokenExpiryDate = null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
        return email.equals(user.email);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return getRole() == Role.ADMIN;
    }
}
