package com.banco_digital;

import com.banco_digital.utilitarios.CodificarImagem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Banco_digitalAppStart {
    public static void main(String[] args) {
        SpringApplication.run(Banco_digitalAppStart.class, args);

      /*  CodificarImagem.decodeImage(
                "/home/cleuton/Imagens/foto/Conhito.txt",
                "/home/cleuton/Imagens/foto/Conhito2.png");  */
    }
}
