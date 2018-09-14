package info.rixlabs.infrastructure.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by riccardo.causo on 12.05.2016.
 */

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    //For me this config can become huge and complicated, can we split it????
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true);

        // Custom JWT based authentication
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) //set the UserDetail service
            .passwordEncoder(passwordEncoder()); //set the encoder
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //@Bean
    //public UserDetailsService userDetailsService() { return new CustomUserdetailService();}
    //Define the encoder for passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
    @Autowired
    AccountRepository accountRepository;

    //Turned into lambda following the sample ! :)

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> {
            Account account = accountRepository.findByUsername(username);

            if(account != null) {
                return new CustomUser(account.getUsername(), account.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("could not find the user '"
                        + username + "'");
            }
        };
    }
    */

}
