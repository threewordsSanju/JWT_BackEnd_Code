package JwtYoutube.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final int TOKEN_VALIDITY = 3600 * 5;
	// hard code signing key random secret key for users
	private static final String secret_key = "learn_programing";

	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// in these it has a higher order function basically it is a function it return
	// a function to caller

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		// this return a claims and no one should change , thats why we made this as a
		// final
		final Claims claims = getAllclaimsFromToken(token);
		return claimResolver.apply(claims);

	}

	private Claims getAllclaimsFromToken(String token) {
//		to define a secret key for secure application
		return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String token, UserDetails userdetail) {
		String userName = getUserNameFromToken(token);
		return (userName.equals(userdetail.getUsername()) && !isTokenExpiRED(token));
	}

	private boolean isTokenExpiRED(String token) {
		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());

	}

	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public String generateToken(UserDetails userdetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userdetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret_key).compact();
	}

}