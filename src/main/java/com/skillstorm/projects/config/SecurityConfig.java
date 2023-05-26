package com.skillstorm.projects.config;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.repositories.GuestRepository;


@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private GuestRepository guestRepository;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.mvcMatchers(HttpMethod.POST, "/login", "/roomtypes","/guests", "/guests/**").permitAll()
//				.mvcMatchers("/reservations","/reservations/**","/rooms").authenticated()
				.anyRequest().permitAll()
				.and()
			.httpBasic()
				.and()
//			.formLogin()
//				.successHandler(authenticationSuccessHandler())
//				.and()
			.cors().and().csrf().disable();

		return http.build();
	}
	
	private AuthenticationSuccessHandler authenticationSuccessHandler() {
		return (request, response, authentication) -> {
			String reservationsPageUrl = determineReservationsPageUrl(authentication);

	        // Adds the reservations page URL to the authentication response
	        response.getWriter().write(reservationsPageUrl);
	        response.setStatus(HttpServletResponse.SC_OK);
	    };
	}

	private String determineReservationsPageUrl(Authentication authentication) {
	    // Extract the necessary information from the authentication object
	    String email = ((UserDetails) authentication.getPrincipal()).getUsername();

	    // Retrieve the guest by email from the GuestRepository
	    Optional<Guest> optionalGuest = guestRepository.findByEmail(email);
	    if (!optionalGuest.isPresent()) {
	        throw new UsernameNotFoundException("Guest not found");
	    }
	    Guest guest = optionalGuest.get();

	    // Get the guest's ID
	    Long guestId = guest.getId();

	    // Construct the reservations page URL based on the guest ID
	    String reservationsPageUrl = "/reservations?guestId=" + guestId;

	    // Return the reservations page URL
	    return reservationsPageUrl;
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}