package br.sc.senac.dw.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.sc.senac.dw.model.entidade.Papel;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private SecurityFilter filter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		//csrf -> Cross-Site Request Forgery (CSRF)
		//https://www.baeldung.com/spring-security-csrf
		
		//Stateless -> não guarda o estado da aplicação (padrão usado no REST)
		//Stateful -> guarda o estado da aplicação
		//https://medium.com/exactaworks/stateless-vs-stateful-f596a6b6471d
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				//Regras incluídas
				//GET em produtos só acessíveis por ADMIN
				//demais métodos permitidos para usuários autenticados (qualquer papel)
				.authorizeHttpRequests(authorize -> authorize
						//.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()    //revisar futuramente
						//.requestMatchers(HttpMethod.POST, "/auth/register").permitAll() //revisar futuramente
						//.requestMatchers(HttpMethod.GET, "/produtos").hasRole(Papel.ADMIN.getNome())
						.anyRequest().permitAll()
				)
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
