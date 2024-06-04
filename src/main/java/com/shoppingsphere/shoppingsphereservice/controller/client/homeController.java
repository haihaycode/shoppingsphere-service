package com.shoppingsphere.shoppingsphereservice.controller.client;

import com.shoppingsphere.shoppingsphereservice.entity.User;
import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import com.shoppingsphere.shoppingsphereservice.service.NotificationService;
import com.shoppingsphere.shoppingsphereservice.service.auth.AuthenticatedUser;
import com.shoppingsphere.shoppingsphereservice.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @Autowired
    HttpSession session;

    @Autowired
    NotificationService notificationService;
    @Autowired
    AuthenticatedUser authenticatedUser;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String home(Model model){

        if (session.getAttribute("success") != null) {
            notificationService.addSuccess(session.getAttribute("success").toString(), 5000);
            notificationService.render(model);
            session.removeAttribute("success");
        }
//        if(authenticatedUser.getAuthenticatedUser()!=null){
//            User user = (User) userService.loadUserByUsername(authenticatedUser.getAuthenticatedUser().getName());
//            notificationService.addSuccess(user.getUsername(), 5000);
//            notificationService.render(model);
//            session.removeAttribute("success");
//        }

        model.addAttribute("breadcrumbs", BreadcrumbPath.HOME.getBreadcrumbs());
        return "index";
    }
}
