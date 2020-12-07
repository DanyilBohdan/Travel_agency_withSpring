package org.bohdan.web.controllers;

import org.bohdan.web.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String welcome() {
        return Path.PAGE_WELCOME;
    }

    @GetMapping(value = "login")
    public String login() {
        return Path.PAGE_LOGIN;
    }

    @GetMapping(value = "login?error")
    public String error() {
        return Path.PAGE_WELCOME;
    }
}
