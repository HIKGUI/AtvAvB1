package br.unipar.programacaoweb;

import br.unipar.programacaoweb.services.UsuarioSIB;
import jakarta.xml.ws.Endpoint;

public class ServiceCepPublisher {

    public static void main(String[] args) {

        Endpoint.publish(
                "http://localhost:8080/usuario",
                new UsuarioSIB());
        System.out.println("Serviço publicado na porta 8080 com sucesso!");
    }

}
