package com.abir.userexpress.user;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
         secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok().body((User) userService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return ResponseEntity.ok().body((User) userService.save(user));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<UserSignInResponse> signIn(@RequestBody UserSignInRequest loggerUser) {
		System.out.println(loggerUser+"\n\n\n\n\n");
		User logger = userService.findByLogger(loggerUser.getUser());
		System.out.println(logger+"\n\n\n\n\n");
		if(passwordEncoder.matches(loggerUser.getPassword(), logger.getPassword())) {
			UserSignInResponse userResponse = new UserSignInResponse();
			userResponse.setName(loggerUser.getUser());
			userResponse.setToken(createToken(logger));
			userResponse.setRefreshToken("refresh token");
			return ResponseEntity.ok().body(userResponse);
		}
		
		return null;
	}
	
	@PostMapping("/validate-token")
	public ResponseEntity<UserSignInResponse> validateToken(@RequestParam String token) {
		return ResponseEntity.ok().body(validateTokenService(token));
	}
	
    public UserSignInResponse validateTokenService(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        User user= userService.findByLogger(login);
        
        UserSignInResponse response = new UserSignInResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setToken(createToken(user));
        response.setRefreshToken("refresh token validate");
        return response;
    }
	
	
	private String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
