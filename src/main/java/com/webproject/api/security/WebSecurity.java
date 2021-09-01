package com.webproject.api.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.webproject.api.userLayer.UserService;




@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	  private final UserService userDetailsService; 
	  
	    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.userDetailsService = userDetailsService;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception { 
	        http
	        .cors().and()
	        .csrf().disable()
	      
	        .authorizeRequests()
	        .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
	        .permitAll()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .anyRequest().authenticated().and().addFilter(getAuthenticationFilter())
	        .addFilter(new AuthorizationFilter(authenticationManager()))
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	    }
	    
	    
	    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		    filter.setFilterProcessesUrl("/users/login");
		    return filter;
		}
//	    
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource()
	    {
	    	final CorsConfiguration configuration = new CorsConfiguration();
	    
	    	configuration.setAllowedOrigins(List.of("http://localhost:3000","https://you.server.domain.com"));
	    	configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
	    	configuration.setAllowCredentials(true);
	    	configuration.setAllowedHeaders(Arrays.asList("*"));
	    	configuration.setExposedHeaders(Arrays.asList("Authorization","UserID"));
	    	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    	source.registerCorsConfiguration("/**", configuration);
	    	
	    	return source;
	    }

}
