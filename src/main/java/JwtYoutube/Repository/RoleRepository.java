package JwtYoutube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JwtYoutube.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
