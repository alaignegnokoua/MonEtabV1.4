package ci.digitalacademy.MonEtabV14;

import ci.digitalacademy.MonEtabV14.models.Address;
import ci.digitalacademy.MonEtabV14.models.Person;
import ci.digitalacademy.MonEtabV14.models.enumeration.Gender;
import ci.digitalacademy.MonEtabV14.services.*;
import ci.digitalacademy.MonEtabV14.services.dto.AddressDTO;
import ci.digitalacademy.MonEtabV14.services.dto.RoleUserDTO;
import ci.digitalacademy.MonEtabV14.services.dto.TeacherDTO;
import ci.digitalacademy.MonEtabV14.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleUserService roleUserService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
