package com.lena;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lena.mapper")
public class TaxtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxtestApplication.class, args);
	}

}
