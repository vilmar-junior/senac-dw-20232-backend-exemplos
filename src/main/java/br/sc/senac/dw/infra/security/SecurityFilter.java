package br.sc.senac.dw.infra.security;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.sc.senac.dw.model.entidade.Usuario;
import br.sc.senac.dw.model.repository.UsuarioRepository;
import br.sc.senac.dw.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	//Interceptará CADA requisição realizada
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = this.recuperarToken(request);
		
		if(token != null) {
			String login = tokenService.validarToken(token);
			UserDetails usuario = usuarioRepository.findByLogin(login);
			
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			
			//Salva na sessão
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		//Encaminha para o filtro seguinte (configurado em SecurityConfiguration)
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		
		if(authHeader != null) {
			token = authHeader.replace("Bearer", ""); //Remove o início padronizado do token
		}
		
		return token;
	}
	
	
	
}
