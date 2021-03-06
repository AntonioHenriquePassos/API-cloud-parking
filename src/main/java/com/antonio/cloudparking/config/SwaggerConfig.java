package com.antonio.cloudparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
@ApiIgnore
public class SwaggerConfig {
	
	@Bean
	public Docket getDocklet() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.antonio.cloudparking"))
				.build()
				.apiInfo(metaData());
		
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Cloud Parking API")
				.description("Spring boot Rest API for Parking")
				.version("1.0.0")
				.license("Apache license  Version 2.0")
				.licenseUrl("https:/www.apache.org/licenses/LICENSE-2.0\"")
				.build();
				
				
	}

}
