package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//da bi mogao koristiti preAuthorize
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/regitrationConfirm.html?token=**", "/public/**", "/public/cinemas/**","/h2-console/**, /cinemas/**").permitAll()// / ili /public/billo sta ne prolazi autent. i moze bilo ko
                .antMatchers("/users/**").hasAuthority("ADMIN")
                .anyRequest().fullyAuthenticated();
 //               .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login")
//                .usernameParameter("email")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")*/
//               // .deleteCookies("remember-me")
               // .logoutSuccessUrl("/")
                //.permitAll();
                //.and()
                //.rememberMe()
                //.and()
               // .sessionManagement()
               // .maximumSessions(1);
        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();
        // http.formLogin().defaultSuccessUrl("/userCreate",true);//uvek baca na tu stranicu
        //ovako baca na tu stranicu samo kad direktno gadjas /login, a inace te prosledi dalje na ono sto si prvobitno gadjao
       // http.formLogin().defaultSuccessUrl("/public/angularUser");
        http.httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    

}