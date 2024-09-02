package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
