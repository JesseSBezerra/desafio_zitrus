package br.com.jdsb.usuarioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UsuarioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioApiApplication.class, args);
    }

}
