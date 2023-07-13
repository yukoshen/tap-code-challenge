package com.kjslocal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.kjslocal.repositories") 
public class TapCodeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapCodeChallengeApplication.class, args);
	}

}
