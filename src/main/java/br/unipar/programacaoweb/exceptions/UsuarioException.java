package br.unipar.programacaoweb.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name = "UsuarioException")
public class UsuarioException extends Exception {

    public UsuarioException(String mensagem) {
        super(mensagem);
    }
}
