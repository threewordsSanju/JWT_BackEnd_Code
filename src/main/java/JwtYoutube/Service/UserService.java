package JwtYoutube.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import JwtYoutube.Entity.Role;
import JwtYoutube.Entity.User;
import JwtYoutube.Repository.RoleRepository;
import JwtYoutube.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private RoleRepository rolerepo;

	@Autowired
	private PasswordEncoder passwordencoder;

	public User adduser(User user) {
		Role role = rolerepo.findById("User").get();
		Set<Role> roles = new HashSet<>();
		roles.add(role);

		System.out.println("addhar id is"+user.getAddharId());
		System.out.println("full name"+user.getFullName());
		user.setRole(roles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return userrepo.save(user);
	}

	public void initrolesAndUsers() {
		Role adminrole = new Role();
		adminrole.setRole("Admin");
		adminrole.setRoleDescription("admin is created");
		rolerepo.save(adminrole);

		Role userrole = new Role();
		userrole.setRole("User");
		userrole.setRoleDescription("user is created");
		rolerepo.save(userrole);

		User adminuser = new User();
		adminuser.setAddharId(1234567891l);
		adminuser.setFullName("rdmin");
		adminuser.setUserName("admin123");
		adminuser.setUserPassword(getEncodedPassword("admin@123"));
		Set<Role> adminroles = new HashSet<>();
		adminroles.add(adminrole);
		adminuser.setRole(adminroles);
		userrepo.save(adminuser);

//		User adminuser1 = new User();
//		adminuser1.setAddharId(998052559612l);
//		adminuser1.setFullName("user");
//		adminuser1.setUserName("user123");
//		adminuser1.setUserPassword(getEncodedPassword("User@123"));
//		Set<Role> adminroles1 = new HashSet<>();
//		adminroles1.add(userrole);
//		adminuser1.setRole(adminroles1);
//		userrepo.save(adminuser1);

	}

	public String getEncodedPassword(String password) {
		return passwordencoder.encode(password);
	}

}
