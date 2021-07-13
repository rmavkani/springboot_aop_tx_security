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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

private final PasswordEncoder passwordEncoder;  

@Autowired 
ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
  this.passwordEncoder = passwordEncoder;
}

@Override
protected void configure(HttpSecurity http)throws Exeption{
    http.csrf().disable().
        .authorizeRequests()
        .antMatchers("/", "index", "/css/*").permitAll()
        .antMatchers("/seyon/api/**").hasRole(PATIENT.name())
        //.antMatchers(HttpMethod.PUT, "/seyon/api/**").hasAuthority(PATIENT_READ.getPermission())
    // .antMatchers(HttpMethod.GET, "/seyon/api/**").hasAuthority(PATIENT_READ.getPermission())
     //   .antMatchers(HttpMethod.POST, "/seyon/api/**").hasAuthority(PATIENT_READ.getPermission())
      //  .antMatchers(HttpMethod.DELETE, "/seyon/api/**").hasAuthority(PATIENT_READ.getPermission())
        .anyRequest().authenticated()
        .and()
        .httpBasic();
}

@Override
@Bean 
protected UserDetailsService userDetailsService(){
  UserDetails patient = User.build()
                          .userName("patient")
                          .password(passwordEncoder.encode("password"))
                          //.roles(PATIENT.name())
                          .authorities(PATIENT.getGrantedAuthorities())
                          .build();
  
  UserDetails admin = User.build()
                          .userName("admin")
                          .password(passwordEncoder.encode("adminpassword"))
                          //.roles(ADMIN.name())
                          .authorities(ADMIN.getGrantedAuthorities())
                          .build();

  return new InMemoryUserDetailsManager(){
    patient, admin 
  }

}