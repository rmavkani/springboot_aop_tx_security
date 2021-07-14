
public class JwtAuthenticationFilter extens UsenamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtConfig jwtConfig;
  private final Secretkey secretKey; 

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager,JwtConfig jwtConfig, Secretkey secretKey ){
    this.authenticationManager =authenticationManager;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey; 
  }

  @Override 
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse respone) throws AuthenticationException{
    try{
      AuthenticationRequest authReq = new ObjectMapper().readValue(request.getInputStream(),AuthenticationRequest.class)
      Authentication auth = new UsernamePasswordAuthenticationToken(authReq.getUserName(), authReq.getPassword()); 
      Authentication authenticate = authenticationManager.authenticate(auth);
      return authenticate;
    }catch(IOExeption e){
      new RuntimeException(e); 
    }

  }

  @Override
  public void successfullAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
    String token = Jwts.builder()
                       .setSubject(authResult.getName())
                       .claim("authorites", authResult.getAuthorities())
                       .setIssuedAt(new Date())
                       .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()))))
                       .setSignWith(secretKey)
                       .compact();
    respone.addHeader(jwtConfig.getAuthorizationHeader() ,jwtConfig.getPrefix()+token);

  }
}