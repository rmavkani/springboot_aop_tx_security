import org.springframework.security.config.anotation.web.configuration.WebSecutiryConfigurerAdapter;
import org.springframework.conntex.anotation.Confguration;
import org.springframework.security.config.anotation.web.builders.HttpSecurity;
import org.springframework.security.config.anotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.userDetailsService; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.beans.factory.anotation.Autowired; 
import org.springframwork.context.anotation.Bean; 

import static com.seyon.security.ApplicationUserRole.*; 


@Confguration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

private final PasswordEncoder passwordEncoder;  

@Autowired 
ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
  this.passwordEncoder = passwordEncoder;
}

@Override
protected void configure(HttpSecurity http)throws Exeption{
    http.authorizeRequests()
        .antMatchers("/", "index", "/css/*")
        .antMatchers("/seyon/api/**").hasRole(PATIENT.name())
        .permitAll().anyRequest().authenticated()
        .and()
        .httpBasic();
}

@Override
@Bean 
protected UserDetailsService userDetailsService(){
  UserDetails patient = User.build()
                          .userName("patient")
                          .password(passwordEncoder.encode("password"))
                          .roles(PATIENT.name())
                          .build();
  
  UserDetails admin = User.build()
                          .userName("admin")
                          .password(passwordEncoder.encode("adminpassword"))
                          .roles(ADMIN.name())
                          .build();

  return new InMemoryUserDetailsManager(){
    patient, admin 
  }

}