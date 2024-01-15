//package com.example.demo.config;
//
//import com.example.demo.user.User;
//import com.example.demo.user.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class Config {
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(11);
//    }
//
//    @Bean
//    CommandLineRunner initDefaultUsers(PasswordEncoder passwordEncoder, UserRepository userRepository) {
//            return args -> {
//                User defaultAdmin = new User(1L, "admin@gmail.com", "admin", "1234", "The admin of the platform","Romania","https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(10).webp");
//                defaultAdmin.setPassword(passwordEncoder.encode(defaultAdmin.getPassword()));
//                userRepository.save(defaultAdmin);
//            };
//    }
//}
