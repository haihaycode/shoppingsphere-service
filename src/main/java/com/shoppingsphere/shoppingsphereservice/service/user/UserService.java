package com.shoppingsphere.shoppingsphereservice.service.user;


import com.shoppingsphere.shoppingsphereservice.dto.UserDTO;
import com.shoppingsphere.shoppingsphereservice.entity.User;
import com.shoppingsphere.shoppingsphereservice.service.BaseService;
import com.shoppingsphere.shoppingsphereservice.service.DTOService;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService, BaseService<User, Integer>, DTOService<UserDTO> {
    User findUserById(Integer id);

    User findByUsername(String username);

    User findByEmail(String email);


    boolean disableUser(String username, boolean enabled);

    boolean changePassword(String username, String password);

    boolean changeFullName(String username, String fullname);

    boolean changeAddress(String username, String address);

    boolean changeGender(String username, boolean gender);

    long getTotalUser();

}
