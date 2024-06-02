package com.shoppingsphere.shoppingsphereservice.controller.auth;

import com.shoppingsphere.shoppingsphereservice.dto.RegisterDTO;
import com.shoppingsphere.shoppingsphereservice.dto.UserDTO;
import com.shoppingsphere.shoppingsphereservice.entity.User;
import com.shoppingsphere.shoppingsphereservice.exception.DuplicateEmailException;
import com.shoppingsphere.shoppingsphereservice.exception.DuplicatePhoneNumberException;
import com.shoppingsphere.shoppingsphereservice.exception.DuplicateUsernameException;
import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import com.shoppingsphere.shoppingsphereservice.service.NotificationService;
import com.shoppingsphere.shoppingsphereservice.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class registerController {
    @Autowired
    public UserService userService;
    @Autowired
    public NotificationService notificationService;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("breadcrumbs", BreadcrumbPath.REGISTER.getBreadcrumbs());
        model.addAttribute("register", new RegisterDTO());
        return "auth/register";
    }
    @PostMapping("/register")
    String register(@Valid @ModelAttribute("register") RegisterDTO register, BindingResult result) {

        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "auth/register";
        }
        
        String fullname = register.getFullname();
        String username = register.getUsername();
        String password = register.getPassword();
        String verifyPassword = register.getVerifyPassword();
        String email = register.getEmail();


        if (!password.equals(verifyPassword)) {
            result.rejectValue("password", "register.password", "Mật khẩu không trùng khớp!");
            result.rejectValue("verifyPassword", "register.verifyPassword", "Mật khẩu không trùng khớp!");
            return "auth/register";
        }

        User user = new User();
        user.setFullname(fullname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(verifyPassword);





        try {
            User u = userService.create(user);
//            if(u!=null) {
//                sendmail.SendEmail(u.getEmail());
//            }else {
//                return "Send mail thất bại";
//            }

            return "redirect:/";
        } catch (DuplicateUsernameException e) {
            e.printStackTrace();
            result.rejectValue("username", "register.username", "Trùng tài khoản!");
            return "auth/register";
        } catch (DuplicateEmailException e) {
            e.printStackTrace();
            result.rejectValue("email", "register.email", "Trùng thư điện tử!");
            return "auth/register";
        } catch (Exception e) {
            e.printStackTrace();
            result.reject("error", "Có lỗi xảy ra!");
            return "redirect:/";
        }


    }


}
