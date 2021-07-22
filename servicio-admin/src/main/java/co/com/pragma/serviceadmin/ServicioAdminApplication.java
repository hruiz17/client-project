package co.com.pragma.serviceadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class ServicioAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAdminApplication.class, args);
	}

}
