package br.unipar.programacaoweb.services;

import br.unipar.programacaoweb.exceptions.ItensPedidoException;
import br.unipar.programacaoweb.exceptions.PedidoException;
import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.models.Pedido;
import br.unipar.programacaoweb.models.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface PedidoSEI {

    @WebMethod
    String salvarNovoPedido(@WebParam(name = "usuario") Usuario usuario,
                            @WebParam(name = "valorTotal") Double valorTotal,
                            @WebParam(name = "observacoes") String observacoes,
                            @WebParam(name = "status") String status)
            throws PedidoException;


    @WebMethod
    List<Pedido> listarPedido() throws PedidoException;

    @WebMethod
    List<ItensPedido> listarItensPedido() throws ItensPedidoException;

    @WebMethod
    String excluirPedido(@WebParam(name = "id") Integer id) throws PedidoException;

}