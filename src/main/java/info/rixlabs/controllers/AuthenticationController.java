package info.rixlabs.controllers;


import info.rixlabs.data.AccountRepository;
import info.rixlabs.models.Account;
import info.rixlabs.models.CustomUser;
import info.rixlabs.models.request.AuthenticationRequest;
import info.rixlabs.models.request.AuthenticationResponse;
import info.rixlabs.security.TokenUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  private final AccountRepository accountRepository;
  private final AuthenticationManager authenticationManager;
  private final TokenUtils tokenUtils;
  private final UserDetailsService userDetailsService;

  @Autowired
  AuthenticationController(AccountRepository accountRepository,
                           AuthenticationManager authenticationManager,
                           TokenUtils tokenUtils,
                           UserDetailsService userDetailsService
                           ){
                           this.accountRepository = accountRepository;
                           this.authenticationManager = authenticationManager;
                           this.tokenUtils = tokenUtils;
                           this.userDetailsService = userDetailsService;
  }

  //@Autowired
  //private UserDetailsService userDetailsService;
  private final Logger logger = Logger.getLogger(this.getClass());

  //@Value("${cerberus.token.header}")
  private String tokenHeader;





  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {

    // Perform the authentication
    Authentication authentication = this.authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-authentication so we can generate token
    UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    String token = this.tokenUtils.generateToken(userDetails);

    // Return the token
    return ResponseEntity.ok(new AuthenticationResponse(token));
  }

  @RequestMapping( method = RequestMethod.GET)
  public ResponseEntity<?> authenticationRequest(HttpServletRequest request,UserDetailsService userDetailsService) {
    String token = request.getHeader(this.tokenHeader);
    String username = this.tokenUtils.getUsernameFromToken(token);
    Account user = (Account) userDetailsService.loadUserByUsername(username);
    if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
      String refreshedToken = this.tokenUtils.refreshToken(token);
      return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @RequestMapping("/token")
  public String token(AccountRepository accountRepository){
    String token;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    /*
    Account user = this.accountRepository.findByUsername("poldo");
    UserDetails aaa =  userDetailsService.loadUserByUsername("poldo");
    */
    if (principal instanceof UserDetails) {
      token = this.tokenUtils.generateToken((UserDetails) principal);
    } else {
      token = "ciao";
    }

    return token;
  }

}
