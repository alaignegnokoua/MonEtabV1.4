package ci.digitalacademy.MonEtabV14;

import ci.digitalacademy.MonEtabV14.services.*;
import ci.digitalacademy.MonEtabV14.services.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String password = passwordEncoder.encode("admin");

		UserDTO user1 = new UserDTO();
		user1.setPseudo("admin");
		user1.setCreationDate(Instant.now());
		user1.setPassword(password);

		userService.save(user1);

		String passwordUser = passwordEncoder.encode("user");

		UserDTO user2 = new UserDTO();
		user2.setPseudo("user");
		user2.setCreationDate(Instant.now());
		user2.setPassword(passwordUser);

		userService.save(user2);

		String passwordOther = passwordEncoder.encode("other");

		UserDTO other = new UserDTO();
		other.setPseudo("other");
		other.setCreationDate(Instant.now());
		other.setPassword(passwordOther);

		userService.save(other);
	}
}
