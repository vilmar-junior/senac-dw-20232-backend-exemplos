package br.sc.senac.dw.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import br.sc.senac.dw.model.entidade.Usuario;

@Service
public class TokenService {
	
	//Chave configurada no arquivo application.properties
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(usuario.getLogin())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			return token;
		}catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar o token de acesso ", e);
		}
	}
	
	public String validarToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		}catch (JWTVerificationException e) {
			return "";
		}
	}
	
	private Instant genExpirationDate() {
		//Gera um tempo 2 horas à frente
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(("-03:00")));
	}
	
}





