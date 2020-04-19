package br.com.bills.repository;

import br.com.bills.model.Bills;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsRepository extends CrudRepository<Bills, Long> {
}
