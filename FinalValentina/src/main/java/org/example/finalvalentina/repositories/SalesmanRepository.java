package org.example.finalvalentina.repositories;

import org.example.finalvalentina.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesmanRepository extends JpaRepository<Salesman,Long> {

    //Interface class to access the list of salesman and find them by id
    List<Salesman> findSalesmanById (long kw);
}
