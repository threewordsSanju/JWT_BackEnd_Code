package JwtYoutube.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JwtYoutube.Entity.Role;
import JwtYoutube.Repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository rolerepo;

	public Role createrole(Role role) {
		return rolerepo.save(role);
	}

}
