package info.rixlabs;

import info.rixlabs.data.AccountRepository;
import info.rixlabs.data.PersonRepository;
import info.rixlabs.models.Account;
import info.rixlabs.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@SpringBootApplication
public class SpringbootSandboxApplication  {

	private static final Logger log = LoggerFactory.getLogger(SpringbootSandboxApplication.class);


	public static void main(String[] args) {

		SpringApplication.run(SpringbootSandboxApplication.class, args);
	}


	// Implement functional interface CommandLineRunner with a lambda
	@Bean
	public CommandLineRunner load(AccountRepository repository, PasswordEncoder passwordEncoder, PersonRepository peopleRepository) {
		return (args) -> {
			// save an account
			repository.save(new Account("poldo", passwordEncoder.encode("poldone") ));


			log.info("---------------SECURITY TEST DATA----------------");
			for (Account account : repository.findAll()) {
				log.info(account.toString());
			}
			log.info("---------------SECURITY TEST DATA----------------");

			log.info("---------------POPLE TEST DATA----------------");


			//Stream.of(["Gino","Pldoni"],["Johnny","Sperso"]).forEach();
			Arrays.asList(new Person("Gino","Pldoni"),new Person("Johnny","Sperso"),new Person("Stack","Asino")).stream().forEach(peopleRepository::save);
//					person -> peopleRepository.save(person)
//			);

			peopleRepository.findAll().forEach(System.out::println);




		};
	}
}

