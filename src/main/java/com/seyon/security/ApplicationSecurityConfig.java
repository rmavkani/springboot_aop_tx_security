import org.springframework.security.config.anotation.web.configuration.WebSecutiryConfigurerAdapter;
import org.springframework.conntex.anotation.Confguration;
import org.springframework.security.config.anotation.web.builders.HttpSecurity;
import org.springframework.security.config.anotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.userDetailsService; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.beans.factory.anotation.Autowired; 
import org.springframework.context.anotation.Bean; 
import org.springframework.security.web.csrf.CookiesCsrfTokenRepository;

import static com.seyon.security.ApplicationUserRole.*; 


@Confguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

private final PasswordEncoder passwordEncoder;  
private final UserDetailsService userDetailsService;
private final SecretKey secretKey;
private final JwtConfig jwtConfig;

@Autowired 
ApplicationSecurityConfig(PasswordEncoder passwordEncoder, 
                          UserDetailsService userDetailsService,
                          SecretKey secretKey,
                          JwtConfig jwtConfig){
  this.passwordEncoder = passwordEncoder;
  this.userDetailsService= userDetailsService;
  this.secretKey = secretKey;
  this.jwtConfig=jwtConfig;
}

@Override
protected void configure(HttpSecurity http)throws Exeption{

    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(new JwtAuthencticationFilter(authenticationManager(), jwtConfig, secretKey))
        .addFilterAfter(new JwtTokenVerifier(secretKey,jwtConfig),JwtAuthencticationFilter.class ) 
           
    
    /* DB Auth based on Formbased 
    http.csrf().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/","index").permitAll()
        .antMatchers("/seyon/api/**").hasRole(PATIENT.name())
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .defaultSuccessURl("/index", true)
          .passwordParameter("password")
          .usernameParameter("username")
        .and()
        .rememberMe()
          .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
          .key("strongvalidkey")
          .rememberMeParameter("remember-me")
        .and()
        .logout()
          .logoutUrl("/logout")
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
          .clearAuthentication(true)
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID", "remember-me")
          .logoutSucessfulUrl("/login")
    */
    /* CSRF token auth
        http.csrf()
        .csrfTokenRepository(CookiesCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .authorizeRequests()
        .antMatchers("/","index").permitAll()
        .antMatchers("/seyon/api/**").hasRole(PATIENT.name())
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
    */
    
    /* HTTP Basic - Auth 
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
        .httpBasic(); */
}

/* InMemoryUserDetailsManager
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
  } */

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userDetailsService);
    return provider; 
  }

}