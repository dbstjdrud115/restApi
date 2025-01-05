package com.li.restApiStudy.global.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitTest {

    @Bean
    CommandLineRunner test(){
        System.out.println("@Bean부여된 CommandLineRunner");
        System.out.println("초기에 시행되는지 테스트");
        return args -> {
            System.out.println("CommandLineRunner 실행됨");
        };
    }
}
