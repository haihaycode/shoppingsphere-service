package com.shoppingsphere.shoppingsphereservice.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testAdminController {
    @GetMapping("/admin")
    public String text(){
       return "cart/cart";
    }
}
