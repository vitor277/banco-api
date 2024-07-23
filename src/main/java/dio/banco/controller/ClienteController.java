package dio.banco.controller;

import dio.banco.model.Cliente;
import dio.banco.service.ApiService;
import dio.banco.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private final ClienteService clienteService;





    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @GetMapping()
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        var cliente = clienteService.buscarTodos();
        return  ResponseEntity.ok(cliente);
    }

   /* @GetMapping("{id}")
    public Banco buscarBanco(@PathVariable Integer id){
        return service.consultarBanco(id);
    }*/

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
        var clienteCriado = clienteService.cadastrar(cliente);
        URI local = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteCriado.getId()).toUri();
        return ResponseEntity.created(local).body(clienteCriado);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,
                                             @RequestBody Cliente cliente){
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }


}
