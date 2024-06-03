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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

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
    public String register(@Valid @ModelAttribute("register") RegisterDTO register, BindingResult result, RedirectAttributes redirectAttributes) {

        String view = "auth/register";

        if (result.hasErrors()){
            return view;
        }
        if (!register.getPassword().equals(register.getVerifyPassword())) {
            String errorMsg = "Mật khẩu không trùng khớp!";
            result.rejectValue("password", "register.password", errorMsg);
            result.rejectValue("verifyPassword", "register.verifyPassword", errorMsg);
            return view;
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(register.getPassword());


        try {
            userService.create(user);
            notificationService.addSuccess("Đăng ký thành công vui lòng đăng nhập",5000);
            notificationService.renderRedirect(redirectAttributes);
            view = "redirect:/login";
        } catch(DuplicateUsernameException ex) {
            result.rejectValue("username", "register.username", "Trùng tài khoản!");
        } catch(DuplicateEmailException ex) {
            result.rejectValue("email", "register.email", "Trùng thư điện tử!");
        } catch(Exception ex) {
            notificationService.addError("Đăng ký thất bại ",5000);
            notificationService.render(redirectAttributes);
            view = "redirect:/";
        }

        return view;
    }
}
