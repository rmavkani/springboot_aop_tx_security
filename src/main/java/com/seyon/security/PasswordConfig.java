import org.springframework.context.anotation.Configuration;
import org.springframework.context.anotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 


@Configuration 
public class PasswordConfig{

@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(10);
  }

}