package com.shoppingsphere.shoppingsphereservice.controller.auth;

import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import com.shoppingsphere.shoppingsphereservice.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @Autowired
    HttpSession session;
    @Autowired
    NotificationService notificationService;
    @GetMapping("/login")
    public String login(Model model , @Param("error") String error){

        if (session.getAttribute("error") != null) {
            notificationService.addError(session.getAttribute("error").toString(), 5000);
            notificationService.render(model);
            session.removeAttribute("error");
        }
        if(error!=null){
            notificationService.addWarning("Vui lòng đăng nhập ! ", 5000);
            notificationService.render(model);
        }

        model.addAttribute("breadcrumbs", BreadcrumbPath.LOGIN.getBreadcrumbs());
        return "auth/login";
    }



}
