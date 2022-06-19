package JwtYoutube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JwtYoutube.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


}
