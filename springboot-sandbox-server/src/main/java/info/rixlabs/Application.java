package info.rixlabs;

import info.rixlabs.core.domain.Account;
import info.rixlabs.core.domain.AccountRepository;
import info.rixlabs.core.domain.Person;
import info.rixlabs.core.domain.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}


	// Implement functional interface CommandLineRunner with a lambda
	@Bean
	public CommandLineRunner load(AccountRepository repository, PasswordEncoder passwordEncoder, PersonRepository peopleRepository) {
		return (args) -> {
			// save an account
			repository.save(new Account("poldo", passwordEncoder.encode("poldone") ));
			repository.save(new Account("gino", passwordEncoder.encode("poldoni") ));


			LOGGER.info("---------------SECURITY TEST DATA----------------");
			for (Account account : repository.findAll()) {
				LOGGER.info(account.toString());
			}



			//Stream.of(["Gino","Pldoni"],["Johnny","Sperso"]).forEach();
			Arrays.asList(new Person("Gino","Pldoni"),new Person("Johnny","Sperso"),new Person("Stack","Asino")).stream().forEach(peopleRepository::save);
//					person -> peopleRepository.save(person)
//			);

			peopleRepository.findAll().forEach(System.out::println);




		};
	}
}

