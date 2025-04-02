package br.unipar.programacaoweb;


import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.services.*;
import jakarta.xml.ws.Endpoint;

public class ServiceCepPublisher {

    public static void main(String[] args) {

        Endpoint.publish(
                "http://localhost:8080/usuario",
                new UsuarioSIB());
        System.out.println("Serviço publicado na porta 8080 com sucesso! - Usuario");

        Endpoint.publish(
                "http://localhost:8080/pizza",
                new PizzaSIB());
        System.out.println("Serviço publicado na porta 8080 com sucesso! - Pizza");

        Endpoint.publish(
                "http://localhost:8080/borda",
                new BordaSIB());
        System.out.println("Serviço publicado na porta 8080 com sucesso! - Borda");

        Endpoint.publish(
                "http://localhost:8080/pedido",
                new PedidoSIB());
        System.out.println("Serviço publicado na porta 8080 com sucesso! - Pedido");

        Endpoint.publish(
                "http://localhost:8080/itensPedido",
                new ItensPedidoSIB());
        System.out.println("Serviço publicado na porta 8080 com sucesso! - ItensPedido");
    }

}
