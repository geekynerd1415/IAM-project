package com.iamlab.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StandaloneIamPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(StandaloneIamPlatformApplication.class, args);
	}

}

// package com.iamlab.platform;

// import com.iamlab.platform.service.UserService;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class StandaloneIamPlatformApplication {

//     public static void main(String[] args) {

//         var context =
//                 SpringApplication.run(
//                         StandaloneIamPlatformApplication.class,
//                         args
//                 );

//         UserService userService = context.getBean(UserService.class);

//         userService.testRepository();
//     }
// }