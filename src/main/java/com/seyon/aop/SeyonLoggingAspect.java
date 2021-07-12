
@Component 
@Aspect 
public class SeyonLoggingAspect{
  private Logger log = Logger.getLogger(SeyonLoggingAspect.class.getName());

  @Pointcuts("@target(org.springframework.stereotype.Service)")
  public void serviceOperations(){

  }

  @Before("serviceOperations()")
  public void logBeforeAdvice(){
    log.info("Seyon Service method being executed");
  }

  @After("serviceOperations()")
  public void logAfterAdvice(){
    log.info("Seyon Service method execution completed!");
  }

}