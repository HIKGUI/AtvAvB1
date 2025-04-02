package br.unipar.programacaoweb.services;


import br.unipar.programacaoweb.daos.PizzaDAO;
import br.unipar.programacaoweb.exceptions.PizzaException;
import br.unipar.programacaoweb.models.Pizza;
import jakarta.jws.WebService;

import java.util.List;


@WebService(endpointInterface =
        "br.unipar.programacaoweb.services.PizzaSEI")
public class PizzaSIB implements PizzaSEI {


    @Override
    public String salvarNovoPizza(String sabor) throws PizzaException {
        Pizza pizza = new Pizza(sabor);
        PizzaDAO dao = new PizzaDAO();
        dao.salvar(pizza);

        return "Sabor salvo com sucesso!";
    }

    @Override
    public Pizza editarPizza(Pizza pizza) throws PizzaException {
        PizzaDAO dao = new PizzaDAO();
        Pizza pizzaEditado = dao.buscarPorId(pizza.getId());

        if(pizzaEditado != null ) {
            pizzaEditado.setSabor(pizza.getSabor());

            PizzaDAO daoEditar = new PizzaDAO();
            daoEditar.atualizar(pizzaEditado);
            return pizzaEditado;
        }
        return null;
    }

    @Override
    public List<Pizza> listarPizza() {
        PizzaDAO dao = new PizzaDAO();
        return dao.buscarTodos();
    }

    @Override
    public String excluirPizza(Integer id) throws PizzaException {
        PizzaDAO dao = new PizzaDAO();
        if(!dao.excluir(id)) {
            throw new PizzaException("Erro ao excluir sabor!");
        }

        return "Sabor excluído com sucesso!";
    }

    private boolean pizzaExiste(String sabor) {
        PizzaDAO dao = new PizzaDAO();
        Pizza pizza = dao.buscarPorSabor(sabor);

        if(pizza == null) {
            return false;
        } else {
            return true;
        }

    }

    private void salvarNovoPizza(Pizza pizza) {
        PizzaDAO dao = new PizzaDAO();
        dao.salvar(pizza);
    }

}
