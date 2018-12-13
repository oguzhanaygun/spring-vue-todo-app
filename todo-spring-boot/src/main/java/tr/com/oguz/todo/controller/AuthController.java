package tr.com.oguz.todo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tr.com.oguz.todo.payload.ApiResponse;
import tr.com.oguz.todo.payload.JwtAuthenticationResponse;
import tr.com.oguz.todo.payload.LoginRequest;
import tr.com.oguz.todo.payload.SignUpRequest;
import tr.com.oguz.todo.persistence.entity.user.User;
import tr.com.oguz.todo.persistence.entity.user.UserRoleEnum;
import tr.com.oguz.todo.security.JwtTokenProvider;
import tr.com.oguz.todo.service.user.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
		boolean admin = authentication.getAuthorities().stream()
				.anyMatch(a -> UserRoleEnum.ROLE_ADMIN.name().equals(a.getAuthority()));
		response.setAdmin(admin);
		response.setUser(userService.get(tokenProvider.getUserIdFromJWT(jwt)));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		boolean success = true;
		ResponseEntity<?> response = null;
		if (userService.checkUserName(signUpRequest.getUsername())) {
			success = false;
			response = ResponseEntity.badRequest().body(new ApiResponse(success, "Username is already taken!"));
		}

		if (userService.checkUserMail(signUpRequest.getEmail())) {
			success = false;
			response = ResponseEntity.badRequest().body(new ApiResponse(success, "Email Address already in use!"));
		}

		if (success) {
			User savedUser = userService.save(signUpRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
					.buildAndExpand(savedUser.getUsername()).toUri();
			response = ResponseEntity.created(location).body(new ApiResponse(success, "ok"));
		}

		return response;
	}
}
