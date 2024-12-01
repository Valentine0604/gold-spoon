package org.valentine.goldspoon.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin access";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User access";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public access";
    }
}
