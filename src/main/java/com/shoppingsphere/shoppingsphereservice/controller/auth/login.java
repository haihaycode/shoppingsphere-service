package com.shoppingsphere.shoppingsphereservice.controller.auth;

import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class login {
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("breadcrumbs", BreadcrumbPath.LOGIN.getBreadcrumbs());
        return "auth/login";
    }



}
