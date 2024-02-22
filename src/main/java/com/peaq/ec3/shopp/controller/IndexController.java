package com.peaq.ec3.shopp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("details")
    public String details() {
        return "details";
    }

    @RequestMapping("header")
    public String header() {
        return "header";
    }

    @RequestMapping("footer")
    public String footer() {
        return "footer";
    }

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("order")
    public String order() {
        return "order";
    }

    @RequestMapping("account")
    public String account() {
        return "account";
    }


}
