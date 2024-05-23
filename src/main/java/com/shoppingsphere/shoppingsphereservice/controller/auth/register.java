package com.shoppingsphere.shoppingsphereservice.controller.auth;

import com.shoppingsphere.shoppingsphereservice.model.Breadcrumb;
import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class register {
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("breadcrumbs", BreadcrumbPath.REGISTER.getBreadcrumbs());
        return "auth/register";
    }
}
