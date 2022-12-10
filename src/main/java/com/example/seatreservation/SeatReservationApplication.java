package com.example.seatreservation;
import com.example.seatreservation.entities.Seat;
import com.example.seatreservation.respositories.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
@SpringBootApplication
public class SeatReservationApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeatReservationApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(SeatRepository seatRepository){
		return args -> {
			seatRepository.save(new Seat(1,  "Test", "1D", new Date()));
			seatRepository.save(new Seat(2,  "Test", "1D", new Date()));
			seatRepository.save(new Seat(3,  "Test", "1D", new Date()));
			seatRepository.save(new Seat(4,  "Test", "1D", new Date()));
			seatRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});
		};
	}
}