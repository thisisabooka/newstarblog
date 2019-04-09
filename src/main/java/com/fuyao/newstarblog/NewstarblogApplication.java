package com.fuyao.newstarblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:kaptcha/kaptcha.xml"})    //开启Kaptcha验证码
@SpringBootApplication
public class NewstarblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewstarblogApplication.class, args);
    }

}
