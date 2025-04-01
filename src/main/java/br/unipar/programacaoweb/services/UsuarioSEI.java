package br.unipar.programacaoweb.services;

import br.unipar.programacaoweb.exceptions.UsuarioException;
import br.unipar.programacaoweb.models.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface UsuarioSEI {

    @WebMethod
    String boasVindas(@WebParam(name = "nome") String nome,
                      @WebParam(name = "telefone") String telefone,
                      @WebParam(name = "cpf") String cpf,
                      @WebParam(name = "endereco") String endereco,
                      @WebParam(name = "dataNascimento") String dtNascimento)
            throws UsuarioException;

    @WebMethod
    String salvarNovoUsuario(@WebParam(name = "nome") String nome,
                             @WebParam(name = "telefone") String telefone,
                             @WebParam(name = "cpf") String cpf,
                             @WebParam(name = "endereco") String endereco,
                             @WebParam(name = "dataNascimento") String dtNascimento)
            throws UsuarioException;

    @WebMethod
    Usuario editarUsuario(@WebParam Usuario usuario) throws UsuarioException;

    @WebMethod
    List<Usuario> listarUsuarios() throws UsuarioException;

    @WebMethod
    String excluirUsuario(@WebParam(name = "id") Integer id) throws UsuarioException;

}