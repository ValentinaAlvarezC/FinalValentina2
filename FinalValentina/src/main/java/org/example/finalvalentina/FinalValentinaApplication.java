package org.example.finalvalentina;

import org.example.finalvalentina.entities.Salesman;
import org.example.finalvalentina.repositories.SalesmanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class FinalValentinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalValentinaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SalesmanRepository salesmanRepository){
        return args -> {
            salesmanRepository.save(new Salesman(null, 23, "Jessica Lam", "Washing Machine", 5000.0, new Date()));
            salesmanRepository.save(new Salesman(null, 23, "Jessica Lam", "Refrigerator", 5000.0, new Date()));
            salesmanRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });
        };
    }

}
