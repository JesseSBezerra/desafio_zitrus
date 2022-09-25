package br.com.jdsb.enderecoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EnderecoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnderecoApiApplication.class, args);
    }

}
