package br.unipar.programacaoweb.services;


import br.unipar.programacaoweb.daos.ItensPedidoDAO;
import br.unipar.programacaoweb.daos.PedidoDAO;
import br.unipar.programacaoweb.exceptions.PedidoException;
import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.models.Pedido;
import br.unipar.programacaoweb.models.Usuario;
import jakarta.jws.WebService;

import java.util.List;


@WebService(endpointInterface =
        "br.unipar.programacaoweb.services.PedidoSEI")
public class PedidoSIB implements PedidoSEI {

    @Override
    public String salvarNovoPedido(Usuario usuario, Double valorTotal, String observacoes, String status) throws PedidoException {
        Pedido pedido = new Pedido(usuario, valorTotal, observacoes, "Recebido");
        PedidoDAO dao = new PedidoDAO();
        dao.salvar(pedido);

        return "Pedido salvo com sucesso!";    }

//    @Override
//    public Pedido editarPedido(Pedido pedido) throws PedidoException {
//        PedidoDAO dao = new PedidoDAO();
//        Pedido pedidoEditado = dao.buscarPorId(pedido.getId());
//
//        if(pedidoEditado != null ) {
//            pedidoEditado.setId(pedido.getSabor());
//
//            PedidoDAO daoEditar = new PedidoDAO();
//            daoEditar.atualizar(pedidoEditado);
//            return pedidoEditado;
//        }
//        return null;
//    }
    //Fazer altomatico

    @Override
    public List<Pedido> listarPedido() {
        PedidoDAO dao = new PedidoDAO();
        return dao.buscarTodos();

    }

        @Override
        public List<ItensPedido> listarItensPedido() {
        ItensPedidoDAO dao = new ItensPedidoDAO();
        return dao.buscarTodos();
    }

    @Override
    public String excluirPedido(Integer id) throws PedidoException {
        PedidoDAO dao = new PedidoDAO();
        if(!dao.excluir(id)) {
            throw new PedidoException("Erro ao excluir Pedido!");
        }

        return "Pedido excluído com sucesso!";
    }

//    private boolean pedidoExiste(Integer id) {
//        PedidoDAO dao = new PedidoDAO();
//        Pedido pedido = dao.buscarPorPedido(Integer id);
//
//        if(pedido == null) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }

    private void salvarNovoPedido(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        dao.salvar(pedido);
    }



}
