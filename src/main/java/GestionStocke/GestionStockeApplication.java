package GestionStocke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

//@EnableJpaAuditing
//@ComponentScan("GestinStocke.MvtStkService")
public class GestionStockeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStockeApplication.class, args);
	}

}
