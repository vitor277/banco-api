package dio.banco;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {SpringApplication.run(BancoApplication.class, args);}

}
