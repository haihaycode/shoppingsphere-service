package com.shoppingsphere.shoppingsphereservice.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class checkOutController {
    @GetMapping("")
    public String checkOut(){
        return "cart/checkout";
    }
}
