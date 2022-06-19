package JwtYoutube.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import JwtYoutube.Entity.JwtRequest;
import JwtYoutube.Entity.JwtResponse;
import JwtYoutube.Entity.User;
import JwtYoutube.Repository.UserRepository;
import JwtYoutube.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private AuthenticationManager authenticationmanager;

	@Autowired
	private JwtUtil jwtutil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepo.findById(username).get();

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					getAuthorities(user));
		}

		else {
			throw new UsernameNotFoundException("username is not valid");
		}

	}

	public JwtResponse createJwtToken(JwtRequest jwtrequest) throws Exception {

		String userName = jwtrequest.getUserName();
		String userPassword = jwtrequest.getUserPassword();
		authnticate(userName, userPassword);

		final UserDetails userdetails = loadUserByUsername(userName);
		String newGenratedToken = jwtutil.generateToken(userdetails);
		User user = userrepo.findById(userName).get();

		return new JwtResponse(user, newGenratedToken);
	}

	private Set getAuthorities(User user) {
		Set authorities = new HashSet<>();

		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		});

		return authorities;

	}

	public void authnticate(String userName, String userPassword) throws Exception {
		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("user is diabled");
		} catch (BadCredentialsException e) {
			throw new Exception("bad user credintials");
		}
	}
}