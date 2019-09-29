package com.agiraud.charon.resource.cassandra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.agiraud.charon.core.dao.UserService;
import com.agiraud.charon.core.dto.User;
import com.agiraud.charon.resource.dto.UserEmail;
import com.agiraud.charon.resource.dto.UserPhone;
import com.agiraud.charon.resource.dto.UserProfile;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserRestController {
	/* ************************************************************************* */
	// ATTRIBUTES
	/* ************************************************************************* */
	@Autowired
	private UserService userService;

	/* ************************************************************************* */
	// REQUEST MAPPING
	/* ************************************************************************* */
	@PreAuthorize("#oauth2.hasScope('profile')")
	@GetMapping("/me")
	public ResponseEntity<UserProfile> me() {
		return ResponseEntity.ok(new UserProfile(getUser()));
	}

	@PreAuthorize("#oauth2.hasScope('email')")
	@GetMapping("/email")
	public ResponseEntity<UserEmail> email() {
		return ResponseEntity.ok(new UserEmail(getUser()));
	}

	@PreAuthorize("#oauth2.hasScope('phone')")
	@GetMapping("/phone")
	public ResponseEntity<UserPhone> phone() {
		return ResponseEntity.ok(new UserPhone(getUser()));
	}

	public ResponseEntity<Collection<User>> getAll(WebRequest request, Model model) {
		Collection<User> users = userService.getAll();
		return ResponseEntity.ok(users);
	}

	/* ************************************************************************* */
	// PRIVATE FUNCTIONS
	/* ************************************************************************* */
	private User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return userService.getByUsername(username);
	}
}
