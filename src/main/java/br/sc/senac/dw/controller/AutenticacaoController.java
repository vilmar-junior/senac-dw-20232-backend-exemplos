package br.sc.senac.dw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.model.dto.AutenticacaoDTO;
import br.sc.senac.dw.model.entidade.Usuario;
import br.sc.senac.dw.model.repository.UsuarioRepository;
import br.sc.senac.dw.service.TokenService;

@RestController
@RequestMapping(path = "/auth") 
@CrossOrigin(origins = {"http://localhost:4200","http://127.0.0.1:5500"}, maxAge = 3600)
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("login")
	public ResponseEntity login(@RequestBody AutenticacaoDTO dto) {
		
		UsernamePasswordAuthenticationToken usernamePasswordToken = 
					new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
		Authentication auth = this.authenticationManager.authenticate(usernamePasswordToken);
		
		String token = tokenService.gerarToken((Usuario) auth.getPrincipal());
		
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("register")
	public ResponseEntity register(@RequestBody Usuario novoUsuario) {

		if(this.usuarioRepository.findByLogin(novoUsuario.getLogin()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String senhaCifrada = new BCryptPasswordEncoder().encode(novoUsuario.getSenha());
		novoUsuario.setSenha(senhaCifrada);
		
		usuarioRepository.save(novoUsuario);
		
		return ResponseEntity.ok().build();
	}
}
