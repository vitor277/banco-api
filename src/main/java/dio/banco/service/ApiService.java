package dio.banco.service;


import dio.banco.model.Banco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "banks", url = "https://brasilapi.com.br/api/banks/v1")
public interface ApiService {

    @GetMapping("{codigo}")
    Banco consultarBanco(@PathVariable Integer codigo);

}
