package br.unipar.programacaoweb.services;


import br.unipar.programacaoweb.daos.BordaDAO;
import br.unipar.programacaoweb.daos.ItensPedidoDAO;
import br.unipar.programacaoweb.daos.PedidoDAO;
import br.unipar.programacaoweb.daos.PizzaDAO;
import br.unipar.programacaoweb.exceptions.ItensPedidoException;
import br.unipar.programacaoweb.models.Borda;
import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.models.Pedido;
import br.unipar.programacaoweb.models.Pizza;
import jakarta.jws.WebService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import java.util.List;


@WebService(endpointInterface =
        "br.unipar.programacaoweb.services.ItensPedidoSEI")
public class ItensPedidoSIB implements ItensPedidoSEI {


    @Override
    public String salvarNovoItensPedido(String tamanho, Integer quantidade, Double valorUnitario, Pizza pizza, Borda borda, Pedido pedido) throws ItensPedidoException {

        try{

            double valorTotal = quantidade * valorUnitario;


            ItensPedido itensPedido = new ItensPedido(tamanho, quantidade, valorUnitario, valorTotal, pizza, borda, pedido);
            ItensPedidoDAO dao = new ItensPedidoDAO();
            BordaDAO bordaDAO = new BordaDAO();
            PizzaDAO pizzaDAO = new PizzaDAO();
            String mensagem = "";

            if ((pizzaDAO.buscarPorId(pizza.getId()) != null) && (bordaDAO.buscarPorId(borda.getId()) != null)) {
                dao.salvar(itensPedido);
                mensagem = "Itens do Pedido salvo com sucesso!";

            }else {
                mensagem = "Erro ao cadastrar Itens do Pedido, sabores invalidos!";
                Integer pizzaEncontrada = null;
                Integer bordaEncontrada = null;

                if (pizzaEncontrada == null) {
                    PizzaSIB pizzaSIB = new PizzaSIB();
                    List<Pizza> todasPizzas = pizzaSIB.listarPizza();

                    mensagem += "\nLista de Pizzas disponíveis:";
                    for (Pizza p : todasPizzas) {
                        mensagem += "\nID: " + p.getId() + " - Sabor: " + p.getSabor();
                    }
                }

                if (bordaEncontrada == null) {
                    BordaSIB bordaSIB = new BordaSIB();
                    List<Borda> todasBordas = bordaSIB.listarBorda();

                    mensagem += "\nLista de Bordas disponíveis:";
                    for (Borda b : todasBordas) {
                        mensagem += "\nID: " + b.getId() + " - Tipo: " + b.getSabor();
                    }
                }
            }



            // Atualizar o valorTotal do pedido
            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedidoAtual = pedidoDAO.buscarPorPedido(pedido.getId());

            if (pedidoAtual != null) {
                double novoValorTotal = pedidoAtual.getValorTotal();
                novoValorTotal += valorTotal;
                pedidoAtual.setValorTotal(novoValorTotal);
                pedidoAtual.setStatus("Recebido");
                pedidoDAO.atualizar(pedidoAtual);

                // AGENDADOR DE STATUS DIRETO AQUI
                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                String[] statusList = {
                        "Em Preparo",
                        "Pronto para Retirada",
                        "Saiu para Entrega",
                        "Entregue"
                };

                final int[] index = {0};

                Runnable task = () -> {
                    if (index[0] < statusList.length) {
                        Pedido pedidoParaAtualizar = pedidoDAO.buscarPorId(pedido.getId());
                        if (pedidoParaAtualizar != null) {
                            pedidoParaAtualizar.setStatus(statusList[index[0]]);
                            pedidoDAO.atualizar(pedidoParaAtualizar);
                            System.out.println("Status atualizado para: " + statusList[index[0]]);
                        }
                        index[0]++;
                    } else {
                        scheduler.shutdown();
                    }
                };

                scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.MINUTES); // executa a cada 1 minuto
            }

            return mensagem;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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
        ItensPedido item = dao.buscarPorId(id);

        if (item == null) {
            throw new ItensPedidoException("Item do Pedido não encontrado para exclusão!");
        }

        // Atualizar o valor do pedido antes de excluir o item
        Pedido pedido = item.getPedido();
        if (pedido != null) {
            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedidoAtual = pedidoDAO.buscarPorPedido(pedido.getId());

            if (pedidoAtual != null) {
                double novoValorTotal = pedidoAtual.getValorTotal() != null ? pedidoAtual.getValorTotal() : 0.0;
                novoValorTotal -= item.getValorTotal(); // subtrai o valor do item excluído
                if (novoValorTotal < 0) novoValorTotal = 0.0;
                pedidoAtual.setValorTotal(novoValorTotal);
                pedidoDAO.atualizar(pedidoAtual);
            }
        }

        // Agora sim exclui o item
        if (!dao.excluir(id)) {
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
