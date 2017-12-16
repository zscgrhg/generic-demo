package com.example.genericdemo;

import com.example.genericdemo.beans.GenericStruct;
import com.example.genericdemo.register.GenericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GenericDemoApplication implements CommandLineRunner{

	@Autowired
	GenericUtil util;
	public static void main(String[] args) {
		SpringApplication.run(GenericDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<GenericStruct<Integer>> beans = util.getBeans(Integer.class);
		System.out.println(beans);
	}
}
