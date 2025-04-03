package br.unipar.programacaoweb.services;

import br.unipar.programacaoweb.exceptions.ItensPedidoException;
import br.unipar.programacaoweb.models.Borda;
import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.models.Pedido;
import br.unipar.programacaoweb.models.Pizza;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ItensPedidoSEI {

    @WebMethod
    String salvarNovoItensPedido(@WebParam(name = "tamanho") String tamanho,
                                 @WebParam(name = "quantidade") Integer quantidade,
                                 @WebParam(name = "valorUnitario") Double valorUnitario,
                                 @WebParam(name = "valorTotal") Double valorTotal,
                                 @WebParam(name = "pizza") Pizza pizza,
                                 @WebParam(name = "borda") Borda borda,
                                 @WebParam(name = "pedido") Pedido pedido)
            throws ItensPedidoException;

    @WebMethod
    ItensPedido editarItensPedido(@WebParam ItensPedido itensPedido) throws ItensPedidoException;

    @WebMethod
    List<ItensPedido> listarItensPedido() throws ItensPedidoException;

    @WebMethod
    String excluirItensPedido(@WebParam(name = "id") Integer id) throws ItensPedidoException;

}