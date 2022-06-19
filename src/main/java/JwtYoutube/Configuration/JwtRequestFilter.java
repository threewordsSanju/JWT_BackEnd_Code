package JwtYoutube.Configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import JwtYoutube.Service.JwtService;
import JwtYoutube.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	// to create that method in try block we are autowiring the Jwtutil
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private JwtService jwtservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// because every time we pass our values in header only
		final String header = request.getHeader("Authorization");
		String jwtToken = null;
		String userName = null;

		// we are just checking if header is not null and if it s starts with "Bearer "
		if (header != null && header.startsWith("Bearer ")) {
			// after we re just trying to get the token except all the values like bearer
			// and space
			jwtToken = header.substring(7);

			try {
				userName = jwtutil.getUserNameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt token");
			} catch (ExpiredJwtException ex) {
				System.out.println("jwt token is expired");
			}

		} else {
			System.out.println("jwt token does n't start with bearer");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userdetails = jwtservice.loadUserByUsername(userName);

			if (jwtutil.validateToken(jwtToken, userdetails)) {
				UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userName, null, userdetails.getAuthorities());

				UsernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken);

			}

		}
filterChain.doFilter(request, response);
	}

}
