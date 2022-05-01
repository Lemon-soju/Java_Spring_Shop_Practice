package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);
}
