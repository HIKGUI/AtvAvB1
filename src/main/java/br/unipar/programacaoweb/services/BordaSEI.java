package br.unipar.programacaoweb.services;

import br.unipar.programacaoweb.exceptions.BordaException;
import br.unipar.programacaoweb.models.Borda;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface BordaSEI {

    @WebMethod
    String salvarNovoBorda(@WebParam(name = "sabor") String sabor)
            throws BordaException;

    @WebMethod
    Borda editarBorda(@WebParam Borda borda) throws BordaException;

    @WebMethod
    List<Borda> listarBorda() throws BordaException;

    @WebMethod
    String excluirBorda(@WebParam(name = "id") Integer id) throws BordaException;

}