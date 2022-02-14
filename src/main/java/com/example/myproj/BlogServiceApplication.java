package com.example.myproj;

//import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.util.logging.Logger;

import static java.util.logging.Logger.*;
//import static jdk.internal.net.http.common.Log.trace;

@EnableSwagger2
@Configuration
@SpringBootApplication
public class BlogServiceApplication {

//	private static  Logger logger1 = getLogger(String.valueOf(BlogServiceApplication.class));



	public static void main(String[] args) {
		SpringApplication.run(BlogServiceApplication.class, args);

//		PropertyConfigurator.configure(System.getProperty("user.dir")+ File.separator+"application.properties");


	}

}
