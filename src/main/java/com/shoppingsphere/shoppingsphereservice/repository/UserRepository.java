package com.shoppingsphere.shoppingsphereservice.repository;

import com.shoppingsphere.shoppingsphereservice.dto.UserDTO;
import com.shoppingsphere.shoppingsphereservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("UPDATE User u SET u.enabled = :disable WHERE u.username = :username")
    void disableUser(@Param("username") String username, @Param("disable") boolean disable);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
    void updatePassword(@Param("username") String username, @Param("password") String password);

    @Modifying
    @Query("UPDATE User u SET u.fullname = :fullname WHERE u.username = :username")
    void updateFullname(@Param("username") String username, @Param("fullname") String fullname);

    @Modifying
    @Query("UPDATE User u SET u.address = :address WHERE u.username = :username")
    void updateAddress(@Param("username") String username, @Param("address") String address);

    @Modifying
    @Query("UPDATE User u SET u.gender = :gender WHERE u.username = :username")
    void updateGender(@Param("username") String username, @Param("gender") boolean gender);

    @Query("SELECT new com.shoppingsphere.shoppingsphereservice.dto.UserDTO(u.id, u.username, u.email, u.phoneNumber, u.enabled) FROM User u")
    Page<UserDTO> getPages(Pageable pageable);

    @Query("SELECT new com.shoppingsphere.shoppingsphereservice.dto.UserDTO(u.id, u.username, u.email, u.phoneNumber, u.enabled) "
            + "FROM User u WHERE u.username like :search OR u.email like :search OR u.phoneNumber like :search")
    Page<UserDTO> getPages(@Param("search") String search, Pageable pageable);
}
