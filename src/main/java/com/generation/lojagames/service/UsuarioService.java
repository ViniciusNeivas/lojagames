package com.generation.lojagames.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;
import com.generation.lojagames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// CADASTRAR
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			
			return Optional.empty();
		
		
		if (calcularIdade (usuario.getDataNascimento()) < 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário tem menos de 18 anos de idade", null);
		
		
		if (usuario.getFoto().isBlank())
			usuario.setFoto("");
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		return Optional.ofNullable(usuarioRepository.save(usuario));
		
	}
	
	// ATUALIZAR
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()));
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já existe", null);
			
		}
		
		return Optional.empty();
	}
	
		public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
			
			Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
			
			
			if (usuario.isPresent()) {
				
				if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
					
					usuarioLogin.get().setId(usuario.get().getId());
					usuarioLogin.get().setNome(usuario.get().getNome());
					usuarioLogin.get().setFoto(usuario.get().getFoto());
					usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(),usuarioLogin.get().getSenha()));
					usuarioLogin.get().setSenha(usuario.get().getSenha());
			
					return usuarioLogin;
					
				}
				
			}
			
			return Optional.empty();
			
		}
		
		
		// CALCULO DE IDADE
		
		private int calcularIdade (LocalDate dataNascimento) {
			
			return Period.between (dataNascimento, LocalDate.now()).getYears();
			
			
		}
		
		
		
		// CRIPTOGRAFIA DA SENHA
		
	private String criptografarSenha(String senha) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode (senha);
		
	}
		
		// COMPARADOR DE SENHA
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);
		
	}
		
		// GERADOR BASIC TOKEN
	
	private String gerarBasicToken (String usuario, String senha) {
		
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		
		return "Basic " + new String (tokenBase64);
	}	
		
}
