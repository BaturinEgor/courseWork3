package ua.khpi.baturin.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.khpi.baturin.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin", "/create", "/update", "/delete")
                .hasAuthority("ADMIN").antMatchers("/user").hasAuthority("USER")
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(successHandler).usernameParameter("j_username")
                .passwordParameter("j_password").failureUrl("/login?error=true")
                .permitAll().and().logout().logoutUrl("/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login").permitAll().and()
                .exceptionHandling()
                .accessDeniedPage("/WEB-INF/pages/accessDenied.jsp").and()
                .csrf().disable();
    }
}