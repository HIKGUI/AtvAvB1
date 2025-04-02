package br.unipar.programacaoweb.services;


import br.unipar.programacaoweb.daos.BordaDAO;
import br.unipar.programacaoweb.exceptions.BordaException;
import br.unipar.programacaoweb.models.Borda;
import jakarta.jws.WebService;

import java.util.List;


@WebService(endpointInterface =
        "br.unipar.programacaoweb.services.BordaSEI")
public class BordaSIB implements BordaSEI {


    @Override
    public String salvarNovoBorda(String sabor) throws BordaException {
        Borda borda = new Borda(sabor);
        BordaDAO dao = new BordaDAO();
        dao.salvar(borda);

        return "Sabor salvo com sucesso!";
    }

    @Override
    public Borda editarBorda(Borda borda) throws BordaException {
        BordaDAO dao = new BordaDAO();
        Borda bordaEditado = dao.buscarPorId(borda.getId());

        if(bordaEditado != null ) {
            bordaEditado.setSabor(borda.getSabor());

            BordaDAO daoEditar = new BordaDAO();
            daoEditar.atualizar(bordaEditado);
            return bordaEditado;
        }
        return null;
    }

    @Override
    public List<Borda> listarBorda() {
        BordaDAO dao = new BordaDAO();
        return dao.buscarTodos();
    }

    @Override
    public String excluirBorda(Integer id) throws BordaException {
        BordaDAO dao = new BordaDAO();
        if(!dao.excluir(id)) {
            throw new BordaException("Erro ao excluir sabor!");
        }

        return "Sabor excluído com sucesso!";
    }

    private boolean bordaExiste(String sabor) {
        BordaDAO dao = new BordaDAO();
        Borda borda = dao.buscarPorSabor(sabor);

        if(borda == null) {
            return false;
        } else {
            return true;
        }

    }

    private void salvarNovoBorda(Borda borda) {
        BordaDAO dao = new BordaDAO();
        dao.salvar(borda);
    }

}
