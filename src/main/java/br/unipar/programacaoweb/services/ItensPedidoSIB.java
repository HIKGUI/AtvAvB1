package br.unipar.programacaoweb.services;


import br.unipar.programacaoweb.daos.ItensPedidoDAO;
import br.unipar.programacaoweb.exceptions.ItensPedidoException;
import br.unipar.programacaoweb.models.Borda;
import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.models.Pedido;
import br.unipar.programacaoweb.models.Pizza;
import jakarta.jws.WebService;

import java.util.List;


@WebService(endpointInterface =
        "br.unipar.programacaoweb.services.ItensPedidoSEI")
public class ItensPedidoSIB implements ItensPedidoSEI {


    @Override
    public String salvarNovoItensPedido(String tamanho, Integer quantidade, Double valorUnitario, Pizza pizza, Borda borda, Pedido pedido) throws ItensPedidoException {

        double valorTotal = quantidade * valorUnitario;


        ItensPedido itensPedido = new ItensPedido(tamanho, quantidade, valorUnitario, valorTotal, pizza, borda, pedido);
        ItensPedidoDAO dao = new ItensPedidoDAO();
        dao.salvar(itensPedido);

        return "Itens do Pedido salvo com sucesso!";
    }

    @Override
    public ItensPedido editarItensPedido(ItensPedido itensPedido) throws ItensPedidoException {
        ItensPedidoDAO dao = new ItensPedidoDAO();
        ItensPedido itensPedidoEditado = dao.buscarPorId(itensPedido.getId());

        if(itensPedidoEditado != null ) {
            itensPedidoEditado.setTamanho(itensPedido.getTamanho());


            ItensPedidoDAO daoEditar = new ItensPedidoDAO();
            daoEditar.atualizar(itensPedidoEditado);
            return itensPedidoEditado;
        }
        return null;
    }

    @Override
    public List<ItensPedido> listarItensPedido() {
        ItensPedidoDAO dao = new ItensPedidoDAO();
        return dao.buscarTodos();
    }

    @Override
    public String excluirItensPedido(Integer id) throws ItensPedidoException {
        ItensPedidoDAO dao = new ItensPedidoDAO();
        if(!dao.excluir(id)) {
            throw new ItensPedidoException("Erro ao excluir Itens do Pedido!");
        }

        return "Itens do Pedido excluído com sucesso!";
    }

    private boolean ItensPedidoExiste(Integer id) {
        ItensPedidoDAO dao = new ItensPedidoDAO();
        ItensPedido itensPedido = dao.buscarPorId(id);

        if(itensPedido == null) {
            return false;
        } else {
            return true;
        }

    }

    private void salvarNovoItensPedido(ItensPedido itensPedido) {
        ItensPedidoDAO dao = new ItensPedidoDAO();
        dao.salvar(itensPedido);
    }

}
