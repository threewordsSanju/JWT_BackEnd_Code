package JwtYoutube.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import JwtYoutube.Entity.JwtRequest;
import JwtYoutube.Entity.JwtResponse;
import JwtYoutube.Service.JwtService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService jwtservice;

	@PostMapping("/auth")
	public JwtResponse createJwtToken( @RequestBody   JwtRequest jwtrequest) throws Exception {
	return jwtservice.createJwtToken(jwtrequest);
	}

}
