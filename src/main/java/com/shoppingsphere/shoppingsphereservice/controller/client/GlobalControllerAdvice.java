package com.shoppingsphere.shoppingsphereservice.controller.client;

import com.shoppingsphere.shoppingsphereservice.dto.UserDTO;
import com.shoppingsphere.shoppingsphereservice.entity.User;
import com.shoppingsphere.shoppingsphereservice.service.auth.AuthenticatedUser;
import com.shoppingsphere.shoppingsphereservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    AuthenticatedUser authenticatedUser;
    @Autowired
    UserService userService;

}
