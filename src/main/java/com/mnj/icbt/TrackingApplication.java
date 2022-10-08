package com.mnj.icbt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		 title = "Real Time School Vehicle Tracking App.",
		version = "0.0.1-SNAPSHOT",
		description = "ST20229589, KG/BSCSD/08/12",
		contact = @Contact(
				name = "Tharaka Manoj",
				email = "trkmanoj95@gmail.com"
		)
))
public class TrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingApplication.class, args);
	}

}
