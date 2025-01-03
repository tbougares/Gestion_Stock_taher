package GestionStocke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableJpaAuditing
//@EnableJpaAuditing
//@ComponentScan("GestinStocke.MvtStkService")
public class GestionStockeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStockeApplication.class, args);
	}

}
