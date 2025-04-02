package br.unipar.programacaoweb.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name = "PizzaException")
public class PizzaException extends Exception {

    public PizzaException(String mensagem) {
        super(mensagem);
    }
}
