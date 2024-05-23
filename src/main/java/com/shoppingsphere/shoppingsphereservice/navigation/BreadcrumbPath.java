package com.shoppingsphere.shoppingsphereservice.navigation;

import com.shoppingsphere.shoppingsphereservice.model.Breadcrumb;

import java.util.Arrays;
import java.util.List;

public enum BreadcrumbPath {
    HOME(Arrays.asList(new Breadcrumb("Home", "/"))),
    REGISTER(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Register", "/register"))),
    LOGIN(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Login", "/login"))),
    PRODUCTS(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Category", "/category"))),
    CART(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Cart", "/cart"))),
    CHECKOUT(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Cart", "/cart"), new Breadcrumb("Checkout", "/checkout"))),
    PROFILE(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Profile", "/profile"))),
    ORDERS(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Profile", "/profile"), new Breadcrumb("Orders", "/orders"))),
    SETTINGS(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Profile", "/profile"), new Breadcrumb("Settings", "/settings"))),
    CONTACT_US(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("Contact Us", "/contactus"))),
    ABOUT_US(Arrays.asList(new Breadcrumb("Home", "/"), new Breadcrumb("About Us", "/aboutUs")));

    private final List<Breadcrumb> breadcrumbs;

    BreadcrumbPath(List<Breadcrumb> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public List<Breadcrumb> getBreadcrumbs() {
        return breadcrumbs;
    }
}
