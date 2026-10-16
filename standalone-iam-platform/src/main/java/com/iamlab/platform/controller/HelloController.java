package com.iamlab.platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from IAM Platform";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name;
    }
}




// package com.iamlab.platform.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.iamlab.platform.dto.CreateUserRequest;

// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/api")
// public class HelloController {

//     @GetMapping("/hello")
//     public String hello() {
//         return "Hello from IAM Platform";
//     }

//     @GetMapping("/hello/{name}")
//     public String helloName(@PathVariable String name) {
//         return "Hello, " + name;
//     }

//     // @GetMapping("/users")
//     // public String getUsers(@RequestParam String role) {
//     // return "Requested users with role: " + role;
//     // }

//     // @GetMapping("/users")
//     // public String getUsers(@RequestParam(required = false) String role) {
//     // return "Requested users with role: " + role;
//     // }

//     @GetMapping("/users")
//     public String getUsers(@RequestParam(defaultValue = "user") String role) {
//         return "Requested users with role: " + role;
//     }

//     @PostMapping("/users")
//     public CreateUserRequest createUser(@Valid  @RequestBody CreateUserRequest request) {
//         return request;
//     }
// }