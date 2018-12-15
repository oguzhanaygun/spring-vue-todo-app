package tr.com.oguz.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.oguz.todo.exception.CustomResponseEntityExceptionHandler;
import tr.com.oguz.todo.payload.FieldError;
import tr.com.oguz.todo.payload.LoginRequest;
import tr.com.oguz.todo.payload.SignUpRequest;
import tr.com.oguz.todo.service.user.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends CustomResponseEntityExceptionHandler {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {

		return ResponseEntity.ok(userService.authenticate(request.getUsernameOrEmail(), request.getPassword()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
		boolean success = true;
		ResponseEntity<?> response = null;
		if (userService.checkUserName(request.getUsername())) {
			success = false;
			response = ResponseEntity.badRequest()
					.body(new FieldError("username", "Username is already taken!", request.getUsername()));
		}

		if (userService.checkUserMail(request.getEmail())) {
			success = false;
			response = ResponseEntity.badRequest()
					.body(new FieldError("email", "Email Address already in use!", request.getEmail()));
		}

		if (success) {
			userService.save(request);
			response = ResponseEntity.ok(userService.authenticate(request.getUsername(), request.getPassword()));
		}

		return response;
	}

}
