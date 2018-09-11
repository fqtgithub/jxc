package com.fqt.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fqt.project.mapper")
public class JxcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JxcApplication.class, args);
	}
}
