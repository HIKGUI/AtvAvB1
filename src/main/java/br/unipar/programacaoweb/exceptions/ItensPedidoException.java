package br.unipar.programacaoweb.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name = "ItensPedidoException")
public class ItensPedidoException extends Exception {

    public ItensPedidoException(String mensagem) {
        super(mensagem);
    }
}
