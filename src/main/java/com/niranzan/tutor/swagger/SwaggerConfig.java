/**
 * 
 */
package com.niranzan.tutor.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Niranjan
 *
 */

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Value("${swagger.title}")
	private String title;
	@Value("${swagger.desc}")
	private String description;
	@Value("${swagger.version}")
	private String version;
	@Value("${swagger.license}")
	private String license;
	@Value("${swagger.license.url}")
	private String licenseUrl;
	@Value("${swagger.contact.name}")
	private String contactNm;
	@Value("${swagger.contact.website}")
	private String website;
	@Value("${swagger.contact.email}")
	private String email;
	@Value("${swagger.base.package}")
	private String basePackage;

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(title).description(description).version(version).license(license)
				.licenseUrl(licenseUrl).contact(new Contact(contactNm, website, email)).build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any()).build().apiInfo(metaData());
	}
}