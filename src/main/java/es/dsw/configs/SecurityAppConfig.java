package es.dsw.configs;

import java.util.ArrayList;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import es.dsw.daos.UsuariosDao;
import es.dsw.models.Usuario;



@Configuration
@EnableWebSecurity
public class SecurityAppConfig {
	
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
      
    	//Esta java annotation únicamente está deshabilitando los warning producto de usar User.withDefaultPasswordEncoder. Realmente dicho método no está deprecate, sino que por seguridad
    	//se recuerda al desarrollador, que esta forma de crear usuarios no garantiza el cifrado de contraseñas. 
    	//Aquí se podría iterar cargando los usuarios que se obtengan desde base de datos.
		
	/*	@SuppressWarnings("deprecation")
		UserDetails user1 = User.withDefaultPasswordEncoder()
		    .username("pepito") //Nombre de usuario
            .password("1234")   //Password
            .roles("admin") //Roles
            .build();
		
		@SuppressWarnings("deprecation")
		UserDetails user2 = User.withDefaultPasswordEncoder()
		    .username("pepita")
            .password("5678")
            .roles("usuario")
            .build();*/
				
		UsuariosDao Usuarios = new UsuariosDao();
		ArrayList<Usuario> objListaUsuario = Usuarios.getAll();
		//String roles = "";
        InMemoryUserDetailsManager InMemory = new InMemoryUserDetailsManager();//new InMemoryUserDetailsManager(user);

       for(Usuario usuario: objListaUsuario) {
    	   StringBuilder roles = new StringBuilder("");
			for(String eachstring: usuario.getRol()) {
				roles.append(eachstring).append(",");
			}
    	    
    	    @SuppressWarnings("deprecation")
    	    UserDetails user = User.withDefaultPasswordEncoder()
    	    .username(usuario.getNombre())
    	    .password(usuario.getPassword())
    	    .roles(roles.toString().split(","))
    	    .build();
    	    
    	    InMemory.createUser(user);
       }
		

		//Se cargan los usuarios.
		//InMemory.createUser(user1);
    	//InMemory.createUser(user2);
       
        //Se devuelve a el modulo de Spring Security el descriptor del objeto InMemoryUserDetailsManager para que surta efecto las modificaciones.
        return InMemory;
    }
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.regexMatchers("/styles/*.*") 
				.permitAll()
			.regexMatchers("/img/*.*")
				.permitAll()
			.regexMatchers("/js/*.*")
				.permitAll() 
			.regexMatchers("/bootstrap/*.*")
				.permitAll() 
			.regexMatchers("/ayuda")
				.permitAll()
			.anyRequest()
				.authenticated()
					.and()
						.formLogin()
							.loginPage("/login")
							.loginProcessingUrl("/loginprocess")
							.permitAll()
					.and()
						.logout()
						.permitAll();
		
		return http.build();	
		
	}
	
	

    //HABILITACIÓN DE LOS EVENTOS onSuccess onFailure (Opcional)
    //Configuración necesaria para la captura de los eventos de login exitoso y login fallido, que se implementan en la clase
    //AuthenticationEvents.java. Para más información: https://docs.spring.io/spring-security/reference/servlet/authentication/events.html
   @Bean
    public AuthenticationEventPublisher authenticationEventPublisher
    (ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

}
