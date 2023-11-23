package com.testemysql.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testemysql.teste.entities.User;
import com.testemysql.teste.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> listarUsuarios() {
		List<User> result = repository.findAll();
		return result;
	}
	
	public User obterUsuarioPorId(Long id) {
		User result = repository.findById(id).orElse(null);
		return result;
	}
	
	public User adicionarUsuario(User user) {
		if (repository.existsByEmail(user.getEmail())) {
			throw new EntityExistsException("Já existe um usuário com o e-mail: " + user.getEmail());
		} else {
			try {
				User result = repository.save(user);
				return result;
			} catch (Exception e) {
				throw new RuntimeException("Erro ao adicionar usuário " + e.getMessage());
			}
		}
	}
	
	public void excluirUsuario(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
		} else {
			try {
				repository.deleteById(id);
			} catch (Exception e) {
				throw new RuntimeException("Erro ao excluir usuário " + e.getMessage());
			}
		}
	}
	
	public User editarUsuario(Long id, User newUser) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
		} else {
			User existingUser = repository.findById(id).get();
			existingUser.setEmail(newUser.getEmail());
			try {
				User result = repository.save(existingUser);
				return result;
			} catch (Exception e) {
				throw new RuntimeException("Erro ao editar usuário " + e.getMessage());
			}
		}
	}
}
