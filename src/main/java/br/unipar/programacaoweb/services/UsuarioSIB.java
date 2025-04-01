package br.unipar.programacaoweb.services;


import br.unipar.programacaoweb.daos.UsuarioDAO;
import br.unipar.programacaoweb.exceptions.UsuarioException;
import br.unipar.programacaoweb.models.Usuario;
import jakarta.jws.WebService;

import java.util.List;


@WebService(endpointInterface =
        "br.unipar.programacaoweb.services.UsuarioSEI")
public class UsuarioSIB implements UsuarioSEI {

    @Override
    public String boasVindas(String nome, String telefone, String cpf, String endereco, String dataNascimento) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setTelefone(telefone);
        usuario.setCpf(cpf);
        usuario.setEndereco(endereco);
        usuario.setDataNascimento(dataNascimento);

        if(usuarioExiste(usuario.getNome())) {
            return "Bem-vindo(a) de volta " + nome;
        } else {
            salvarNovoUsuario(usuario);

            return "Bem-vindo(a) " + nome +
                    ", seus dados foram salvos em nossa base de dados!";
        }
    }

    @Override
    public String salvarNovoUsuario(String nome, String telefone, String cpf, String endereco, String dataNascimento) throws UsuarioException {
        Usuario usuario = new Usuario(nome,telefone,cpf,endereco,dataNascimento);
        UsuarioDAO dao = new UsuarioDAO();
        dao.salvar(usuario);

        return "Usuário salvo com sucesso!";
    }

    @Override
    public Usuario editarUsuario(Usuario usuario) throws UsuarioException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioEditado = dao.buscarPorId(usuario.getId());

        if(usuarioEditado != null ) {
            usuarioEditado.setNome(usuario.getNome());
            usuarioEditado.setTelefone(usuario.getTelefone());
            usuarioEditado.setCpf(usuario.getCpf());
            usuarioEditado.setEndereco(usuario.getEndereco());
            usuarioEditado.setDataNascimento(usuario.getDataNascimento());

            UsuarioDAO daoEditar = new UsuarioDAO();
            daoEditar.atualizar(usuarioEditado);
            return usuarioEditado;
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.buscarTodos();
    }

    @Override
    public String excluirUsuario(Integer id) throws UsuarioException {
        UsuarioDAO dao = new UsuarioDAO();
        if(!dao.excluir(id)) {
            throw new UsuarioException("Erro ao excluir usuário!");
        }

        return "Usuário excluído com sucesso!";
    }

    private boolean usuarioExiste(String nome) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarPorNome(nome);

        if(usuario == null) {
            return false;
        } else {
            return true;
        }

    }

    private void salvarNovoUsuario(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.salvar(usuario);
    }

}
