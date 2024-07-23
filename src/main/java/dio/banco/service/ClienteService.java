package dio.banco.service;

import dio.banco.model.Cliente;

public interface ClienteService {
    Cliente cadastrar(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);
    Cliente buscarPorId(Long id);
    Iterable<Cliente> buscarTodos();


}
