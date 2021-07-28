package com.example.api.configuration;

import com.example.api.generator.TokenGenerator;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.DatabaseStartupValidator;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.security.SecureRandom;

@Configuration
public class AppConfiguration {
  @Bean
  public Tika tika() {
    return new Tika();
  }

  @Bean
  public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
    final DatabaseStartupValidator databaseStartupValidator = new DatabaseStartupValidator();
    databaseStartupValidator.setDataSource(dataSource);
    return databaseStartupValidator;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2PasswordEncoder();
  }

  @Bean
  public TokenGenerator tokenGenerator() {
    return new TokenGenerator(new SecureRandom(), 64);
  }

  @Bean
  public UserDetailsChecker userDetailsChecker() {
    return new AccountStatusUserDetailsChecker();
  }

  @Bean
  public OpenAPI openAPI() {
    final SecurityScheme scheme = new SecurityScheme().type(SecurityScheme.Type.APIKEY).name("X-Token").in(SecurityScheme.In.HEADER);
    final Components components = new Components().addSecuritySchemes("token", scheme);
    final SecurityRequirement securityItem = new SecurityRequirement().addList("token");
    return new OpenAPI()
        .components(components)
        .addSecurityItem(securityItem);
  }
}
