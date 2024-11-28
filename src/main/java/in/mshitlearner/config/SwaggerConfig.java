package in.mshitlearner.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT"
	)
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Multiple Datasource - APIs").version("1.0.0")
				.description(
						"API documentation for Book and User APIs, which manages informations in various databases which are located in different servers.")
				.termsOfService("https://example.com/terms")
				.contact(new Contact().name("Support Team").email("support@example.com").url("https://example.com"))
				.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))  // Apply security globally
				.addTagsItem(new Tag().name("Books").description("Operations related to books"))
				.addTagsItem(new Tag().name("Token").description("Operations related to JWT Token Generation"))
				.addTagsItem(new Tag().name("Users").description("Operations related to users"));
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public").pathsToMatch("/**").build();
	}
}
