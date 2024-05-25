package com.shoppingsphere.shoppingsphereservice.controller.client;

import com.shoppingsphere.shoppingsphereservice.navigation.BreadcrumbPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class productController {

    @GetMapping("/products")
    public String product(Model model){
        model.addAttribute("breadcrumbs", BreadcrumbPath.PRODUCTS.getBreadcrumbs());
        return "newArrivals";
    }
    @GetMapping("/products/{id}")
    public String productDetail(Model model , @PathVariable("id") Optional<String> id){
        return "product/product-detail";
    }
    @GetMapping("/products1")
    public String productDetail1(Model model ){
        return "product/product-detail";
    }
}
