package com.github.saintukrainian.patientdashboard.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String DOCTOR_ROLE = "DOCTOR";
  private static final String ADMIN_ROLE = "ADMIN";

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    UserBuilder users = User.withDefaultPasswordEncoder();

    auth.inMemoryAuthentication()
        .withUser(users.username("Bill_Jackson").password("bill123").roles(DOCTOR_ROLE))
        .withUser(users.username("Administrator").password("admin").roles(DOCTOR_ROLE, ADMIN_ROLE));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/**")
        .hasRole(DOCTOR_ROLE)
        .and()
        .formLogin()
        .and()
        .logout()
        .permitAll();
  }

}
