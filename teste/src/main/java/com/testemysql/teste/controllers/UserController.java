package com.testemysql.teste.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testemysql.teste.entities.User;
import com.testemysql.teste.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public List<User> listarUsuarios() {
		List<User> result = service.listarUsuarios();
		return result;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> obterUsuarioPorId(@PathVariable Long id) {
		User result = service.obterUsuarioPorId(id);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<User> adicionarUsuario(@RequestBody User user) {
		User result = service.adicionarUsuario(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> excluirUsuario(@PathVariable Long id) {
		service.excluirUsuario(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> editarUsuario(@PathVariable Long id, @RequestBody User newUser) {
		User editedUser = service.editarUsuario(id, newUser);
		if (editedUser != null) {
			return ResponseEntity.ok(editedUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
