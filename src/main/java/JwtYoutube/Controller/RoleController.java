package JwtYoutube.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import JwtYoutube.Entity.Role;
import JwtYoutube.Service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleservice;

	@PostMapping("/add")
	public Role createRole(@RequestBody Role role) {
		return roleservice.createrole(role);
	}
}
