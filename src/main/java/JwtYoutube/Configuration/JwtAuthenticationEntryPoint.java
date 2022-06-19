package JwtYoutube.Configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// create a class and implements a interface call authenticationeEntryPoint---> this is a inbuilt interface
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// overiding the unimplemented method called commence
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		// in this method by taking responce send error message by using inbuilt send
		// error method . sc_Unauthorzed pass message along with
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, " your unauthorized");

	}

	//this class is created if any user try to do an wrong thing at instant time this unauthorized message he will getm
	
	
}
