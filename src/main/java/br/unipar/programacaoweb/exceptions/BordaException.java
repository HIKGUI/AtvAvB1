package br.unipar.programacaoweb.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name = "BordaException")
public class BordaException extends Exception {

    public BordaException(String mensagem) {
        super(mensagem);
    }
}
