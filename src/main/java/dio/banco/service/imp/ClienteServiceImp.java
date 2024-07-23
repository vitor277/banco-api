package dio.banco.service.imp;

import dio.banco.model.Banco;
import dio.banco.model.Cliente;
import dio.banco.repository.BancoRepository;
import dio.banco.repository.ClienteRepository;
import dio.banco.service.ApiService;
import dio.banco.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteServiceImp implements ClienteService {

    private ClienteRepository clienteRepository;

    private BancoRepository bancoRepository;

    private ApiService apiService;

    public ClienteServiceImp(ClienteRepository clienteRepository, BancoRepository bancoRepository, ApiService apiService) {
        this.clienteRepository = clienteRepository;
        this.bancoRepository = bancoRepository;
        this.apiService = apiService;
    }

   private Cliente salvarClienteComBanco(Cliente cliente){
            Integer codigoBanco = cliente.getBanco().getCode();
            Banco banco = bancoRepository.findById(codigoBanco).orElseGet(() ->{
                Banco novoBanco = apiService.consultarBanco(codigoBanco);
                bancoRepository.save(novoBanco);
                return novoBanco;
            });
            cliente.setBanco(banco);
            return clienteRepository.save(cliente);
    }
  @Override
    public Cliente cadastrar(Cliente cliente) {
        return salvarClienteComBanco(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd= clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComBanco(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            return clienteBd.get();
        }
        return null;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }
}