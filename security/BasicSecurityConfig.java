package br.org.generation.blogpessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/**
		 *  O objeto auth registra e cria uma nova instância do objeto userDetailsService
		 *  da interface UserDetailsService implementada na Classe UserDetailsServiceImpl
		 *  para recuperar os dados dos usuários gravados no Banco de dados.
		 */
		
		 auth.userDetailsService(userDetailsService);
		 
	}
	
	/**
	 *  O método abaixo é responsável por criptografar a senha do usuário 
	 *  utilizando o método hash Bcrypt.
	 */
		 
		 @Bean
			public PasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
			}
		 
		 
		 
		 @Override
			protected void configure(HttpSecurity http) throws Exception {
			 
			 
			 http.authorizeRequests()
				.antMatchers("/usuarios/logar").permitAll()
				.antMatchers("/usuarios/cadastrar").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().cors()
				.and().csrf().disable();
			 
	}	 
}
