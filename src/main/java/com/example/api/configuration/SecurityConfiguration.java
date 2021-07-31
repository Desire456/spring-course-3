package com.example.api.configuration;

import com.example.api.domain.UserInfo;
import com.example.api.filter.XTokenFilter;
import com.example.api.provider.TokenAuthenticationProvider;
import com.example.api.role.RoleNames;
import com.example.api.role.Roles;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.time.Instant;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Setter(onMethod_ = {@Autowired})
    private TokenAuthenticationProvider tokenAuthenticationProvider;
    @Setter(onMethod_ = {@Autowired})
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/*.png", "/*.jpg");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(tokenAuthenticationProvider)
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .logout().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(new XTokenFilter(authenticationManager()), BasicAuthenticationFilter.class).httpBasic()
                .and()
                .anonymous(o -> o.principal(new UserInfo(
                        -1,
                        "anonymous",
                        "anonymous",
                        null,
                        "anonymous.png",
                        List.of(new SimpleGrantedAuthority(Roles.ANONYMOUS)),
                        true,
                        true,
                        true,
                        true,
                        Instant.ofEpochSecond(0)
                )))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/media").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/registrations").hasRole(RoleNames.ANONYMOUS)
                .antMatchers(HttpMethod.GET, "/api/posts", "/api/posts/{\\d+}").permitAll()
                .antMatchers("/api/**").hasAnyRole(RoleNames.USER, RoleNames.ADMIN)
                .and();
    }
}
