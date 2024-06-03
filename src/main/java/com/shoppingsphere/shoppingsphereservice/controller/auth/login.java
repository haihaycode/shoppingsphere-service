package com.shoppingsphere.shoppingsphereservice.controller.auth;

import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import com.shoppingsphere.shoppingsphereservice.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class login {
    @Autowired
    HttpSession session;
    @Autowired
    NotificationService notificationService;
    @GetMapping("/login")
    public String login(Model model){

        if (session.getAttribute("error") != null) {
            notificationService.addError(session.getAttribute("error").toString(),5000);
            notificationService.render(model);
            session.removeAttribute("error");
        }
        model.addAttribute("breadcrumbs", BreadcrumbPath.LOGIN.getBreadcrumbs());
        return "auth/login";
    }



}
