package JwtYoutube.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	//  just declared a type of methods we are using in this    because we have to declare same thing in .allowedmethods()
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String DELETE="DELETE";
	public static final String PUT="PUT";
	
	// webmvcConfigure is an a interface 
	public WebMvcConfigurer consconfigure() {
		return new WebMvcConfigurer() {
			
			// this is a inbuilt method present in a WebMvcConfigure overide this  add this below methods
			@Override
			public void addCorsMappings(CorsRegistry registry) {
			
			// accepts all type of request thats why we passed ** 
		      registry.addMapping("/**")
		      
		      // accepts this kind of methods only we are declared already above same variables as passed in this
		      .allowedMethods(GET,PUT,DELETE,POST)
		      
		      // all kind of headers are allowed
		      .allowedHeaders("*")
		      
		      // all kind of patterns are allowed
		      .allowedOriginPatterns("*")
		      
		      // Credentials should be true
		      .allowCredentials(true);
			}
		};
	}
}
