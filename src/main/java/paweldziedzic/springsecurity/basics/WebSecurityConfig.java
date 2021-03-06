package paweldziedzic.springsecurity.basics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${adminJanPassword}")
    private String adminJanPassword;

    @Value("${userKarolPassword}")
    private String userKarolPassword;

    @Value("${userMarcoPassword}")
    private String userMarcoPassword;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User userAdmin = new User("Jan",
                getPasswordEncoder().encode(adminJanPassword),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        User userUser = new User("Karol",
                getPasswordEncoder().encode(userKarolPassword),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        User userUser2 = new User("Marco",
                getPasswordEncoder().encode(userMarcoPassword),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(userUser);
        auth.inMemoryAuthentication().withUser(userUser2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/helloAdmin").hasRole("ADMIN")
                .antMatchers("/helloUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/helloSomebody").permitAll()
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/helloUser")
                .and()
                .logout().logoutSuccessUrl("/papa");
    }
}