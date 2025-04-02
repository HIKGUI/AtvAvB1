package br.unipar.programacaoweb.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name = "PedidoException")
public class PedidoException extends Exception {

    public PedidoException(String mensagem) {
        super(mensagem);
    }
}
