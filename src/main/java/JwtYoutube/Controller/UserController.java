package JwtYoutube.Controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import JwtYoutube.Entity.User;
import JwtYoutube.Service.UserService;

@CrossOrigin
@RestController

public class UserController {

	@Autowired
	private UserService userservice;

	@PostConstruct
	public void initrolesAndUsers() {
		userservice.initrolesAndUsers();
	}

	@PostMapping("/requestuser")
	public User adduser(@RequestBody User user) {
		return userservice.adduser(user);
	}

	@GetMapping("/foradmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "this is for admin";
	}

	@GetMapping("/foruser")
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "this is for user";
	}

}
