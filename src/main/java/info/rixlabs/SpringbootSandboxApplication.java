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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
			repository.save(new Account("poldo", new BCryptPasswordEncoder().encode("poldone")));


			log.info("---------------SECURITY TEST DATA----------------");
			for (Account account : repository.findAll()) {
				log.info(account.toString());
			}
			log.info("---------------SECURITY TEST DATA----------------");
		};
	}
}

