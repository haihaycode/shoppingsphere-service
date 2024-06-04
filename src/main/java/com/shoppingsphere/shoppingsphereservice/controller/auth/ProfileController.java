package com.shoppingsphere.shoppingsphereservice.controller.auth;

import com.shoppingsphere.shoppingsphereservice.dto.UserDTO;
import com.shoppingsphere.shoppingsphereservice.entity.User;
import com.shoppingsphere.shoppingsphereservice.service.auth.AuthenticatedUser;
import com.shoppingsphere.shoppingsphereservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class ProfileController {

    @Autowired
    AuthenticatedUser authenticatedUser;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String ManageMyAccount(){
        return "client/account/ManageMyAccount";
    }
    @GetMapping("/MyProfile")
    public String MyProfile(){
        return "client/account/myProfile";
    }
    @GetMapping("/AddressBook")
    public String AddressBook(){
        return "client/account/AddressBook";
    }
    @GetMapping("/TrackYourOrder")
    public String TrackYourOrder(){
        return "client/account/TrackOrder";
    }
    @GetMapping("/myOrders")
    public String MyOrders(){
        return "client/account/MyOrders";
    }
    @GetMapping("/MyReturnsAndCancellations")
    public String MyReturnsAndCancellations(){
        return "client/account/MyReturnsAndCancellations";
    }

    @GetMapping("/wishlist")
    public String wishlist(){
        return "client/wishlist";
    }



}
