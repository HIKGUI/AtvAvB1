package br.unipar.programacaoweb.services;

import br.unipar.programacaoweb.exceptions.PizzaException;
import br.unipar.programacaoweb.models.Pizza;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface PizzaSEI {

    @WebMethod
    String salvarNovoPizza(@WebParam(name = "sabor") String sabor)
            throws PizzaException;

    @WebMethod
    Pizza editarPizza(@WebParam Pizza pizza) throws PizzaException;

    @WebMethod
    List<Pizza> listarPizza() throws PizzaException;

    @WebMethod
    String excluirPizza(@WebParam(name = "id") Integer id) throws PizzaException;

}