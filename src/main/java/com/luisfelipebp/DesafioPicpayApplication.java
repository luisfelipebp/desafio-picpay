package com.luisfelipebp;

import com.luisfelipebp.modelo.Usuario;
import com.luisfelipebp.modelo.enums.TipoUsuarioEnum;
import com.luisfelipebp.repositorio.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioPicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPicpayApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UsuarioRepositorio usuarioRepositorio) {
		return args -> {
			Usuario user1 = new Usuario(null, "Luis Felipe", "12345678910", "luis@gmail.com", "12345", 100, TipoUsuarioEnum.COMUM);
			Usuario user2 = new Usuario(null, "Joao Carlos", "12345678911", "joao@gmail.com", "12345", 50, TipoUsuarioEnum.LOJISTA);
			usuarioRepositorio.save(user1);
			usuarioRepositorio.save(user2);
		};
	}

}
