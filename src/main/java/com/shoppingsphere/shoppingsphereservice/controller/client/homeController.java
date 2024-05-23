package com.shoppingsphere.shoppingsphereservice.controller.client;

import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("breadcrumbs", BreadcrumbPath.HOME.getBreadcrumbs());
        return "index";
    }
}
