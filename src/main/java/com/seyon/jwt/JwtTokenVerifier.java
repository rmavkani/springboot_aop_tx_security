
public class JwtTokenVerifier extens OncePerRequstFilter {
  private final JwtConfig jwtConfig;
  private finat JwtSecretKey jwtSecretKey;

   public JwtTokenVerifier(JwtConfig jwtConfig, JwtSecretKey jwtSecretKey ){
    this.jwtConfig =jwtConfig;
    this.jwtSecretKey =jwtSecretKey;
  }
  
  @Override 
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

    String authorizationHeader = request.getHeader(jwtConfig.getHeader());
    if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
      filterChain(request,response);
      return ;
    }

    String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
    try{
      Jws<Claims> claimsJws = Jwt.parser().setSigningKey(secretKey).parseClaimsJws(token); 
      Claims body = claimsJws.getBody();
      String username=body.getSubject();
      var authorities = (List<Map<String, String>>)body.get("authorities");
      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities
                                                                .stream()
                                                                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                                                                .collect(Collectors.toSet());
      Authentication authentication = new UsernamePasswordAuthencationToken(username, null, simpleGrantedAuthorities); 
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }catch(JwtException e){
      throw new IllegalStateException(e); 
    }

    filterChain(request,response);


  }
  
  
  }