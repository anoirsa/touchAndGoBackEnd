package com.example.springtouchgo.security;


import com.example.springtouchgo.model.appuser.AppUserRole;
import com.example.springtouchgo.service.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests().antMatchers("/","/css*","/js/*").permitAll()
                                     .antMatchers(HttpMethod.GET,"/management/api/v1/data/**").permitAll()
                                    .antMatchers(HttpMethod.POST , "/api/v1/registration").permitAll()
                                    .antMatchers("/api/v1/registration/**").permitAll()
                                    .antMatchers(HttpMethod.POST,"/api/v1/user_and_scores").permitAll()
                                    .antMatchers(HttpMethod.GET,"/api/v1/user_and_scores/**").permitAll()
                                  //  .antMatchers("/management/api/v1/coners").permitAll()
                                    .antMatchers(HttpMethod.POST,"/management/api/v1/data").permitAll()
                                  //  .antMatchers("/management/api/v1/coners").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().permitAll().successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                   // System.out.println("We are handling the login sucess");
                    String username = httpServletRequest.getParameter("username");
                    System.out.println("Username is " + username);
                    httpServletResponse.addCookie(new Cookie("username" , username));
                    httpServletResponse.sendRedirect("http://localhost:3000/gameboard");
        } )//.defaultSuccessUrl("http://localhost:3000/gameboard",true)
                .failureHandler(new AuthenticationFailureHandler() {
                                    @Override
                                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                                        // For testting purposes

                                        System.out.println("Request was rejected");
                                        response.setStatus(900);

                                        Cookie[] cookies = request.getCookies();
                                        for (Cookie cookie :cookies) {
                                           if (cookie.getName().equals("touchandgo") && cookie.getValue().equals("123456")) {
                                               response.addCookie(new Cookie("touchandgo","loginpage"));
                                               response.addCookie(new Cookie("login","error"));
                                               response.sendRedirect("http://localhost:3000/");
                                           }
                                        }
                                        if (request.getRequestURI().contains("localhost:3000")) {
                                            System.out.println("Yes URL contained");
                                          //  response.addCookie(new Cookie("error","login123"));
                                        }
                                    }
                                }
                ).and()
                .logout()
                    .logoutUrl("/logout").permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","remember-me")
                .logoutSuccessUrl("http://localhost:3000/");



    }
    /**
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    } **/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;

    }
}
