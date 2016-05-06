package info.rixlabs;

import info.rixlabs.data.AccountRepository;
import info.rixlabs.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class SpringbootSandboxApplication  {

	private static final Logger log = LoggerFactory.getLogger(SpringbootSandboxApplication.class);


	public static void main(String[] args) {

		SpringApplication.run(SpringbootSandboxApplication.class, args);
	}

	// Implement functional interface CommandLineRunner with a lambda
	@Bean
	public CommandLineRunner load(AccountRepository repository) {
		return (args) -> {
			// save an account
			repository.save(new Account("poldo", "poldone"));


			log.info("---------------SECURITY TEST DATA----------------");
			for (Account account : repository.findAll()) {
				log.info(account.toString());
			}
			log.info("---------------SECURITY TEST DATA----------------");
		};
	}
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {


	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Autowired
	AccountRepository accountRepository;



	//Turned into lambda following the sample ! :)
	@Bean
	public UserDetailsService userDetailsService() {
		return (username) -> {
				Account account = accountRepository.findByUsername(username);

				if(account != null) {
					return new User(account.getUsername(), account.getPassword(), true, true, true, true,
							AuthorityUtils.createAuthorityList("USER"));
				} else {
					throw new UsernameNotFoundException("could not find the user '"
							+ username + "'");
				}
		};
	}
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().
				httpBasic().and().
				csrf().disable();
	}

}
