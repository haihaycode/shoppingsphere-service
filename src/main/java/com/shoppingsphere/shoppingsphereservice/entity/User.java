package com.shoppingsphere.shoppingsphereservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @Column(name = "FULL_NAME", length = 45)
    private String fullname;

    @Column(name = "USERNAME", length = 45, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 128)
    private String password;

    @Column(name = "EMAIL", length = 45, unique = true)
    private String email;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "ENABLED", length = 45)
    private Boolean enabled;

    @Column(name = "GENDER")
    private Boolean gender;

    @Column(name = "PHONE_NUMBER", length=15)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME", insertable = false, updatable = false)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME", insertable = false, updatable = false)
    private Date updatedTime;

//    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
//    @JsonIgnore
//    private List<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(e -> authorities.add(new SimpleGrantedAuthority("ROLE_" + e.getName())));
        return authorities;
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
        return enabled;
    }
}
