package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from "
                        + "users_db where username=?")
                .authoritiesByUsernameQuery("select username, role from roles "
                        + "where username=?");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()

                .antMatchers("/new-car").hasRole("ADMIN")
                .antMatchers("/save-car").hasRole("ADMIN")
                .antMatchers("/update-car").hasRole("ADMIN")
                .antMatchers("/delete-car").hasRole("ADMIN")
                .antMatchers("/new-category").hasRole("ADMIN")
                .antMatchers("/save-category").hasRole("ADMIN")
                .antMatchers("/update-category").hasRole("ADMIN")
                .antMatchers("/delete-category").hasRole("ADMIN")

                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true").permitAll();

        httpSecurity.csrf()
                .ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers()
                .frameOptions()
                .sameOrigin();
    }
}
