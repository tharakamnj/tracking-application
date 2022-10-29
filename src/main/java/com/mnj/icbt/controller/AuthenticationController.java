package com.mnj.icbt.controller;

import com.mnj.icbt.config.CustomUserDetailsService;
import com.mnj.icbt.config.JwtUtil;
import com.mnj.icbt.dto.AuthenticationRequest;
import com.mnj.icbt.dto.AuthenticationResponse;
import com.mnj.icbt.service.ClientService;
import com.mnj.icbt.service.DriverService;
import com.mnj.icbt.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private DriverService driverService;
	@Autowired
	private ClientService clientService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		CommonResponse commonResponse = new CommonResponse();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (Exception e) {
			//throw new Exception("USER_DISABLED", e);
			commonResponse.setStatus(-1);
			commonResponse.setErrorMessages(Collections.singletonList("Invalid user credentials."));
			return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
		}
		/*catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}*/
		
		UserDetails userdetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails.getUsername());
        Object obj = customUserDetailsService.getUserByUsername(authenticationRequest.getUsername());
		if (obj == null){
			commonResponse.setStatus(-1);
			commonResponse.setErrorMessages(Collections.singletonList("Invalid user credentials."));
			return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
		}
		AuthenticationResponse response = new AuthenticationResponse(token,obj);
		commonResponse.setStatus(1);
		commonResponse.setPayload(Collections.singletonList(response));
		return ResponseEntity.ok(commonResponse);
	}

}
