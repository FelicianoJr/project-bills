package br.com.bills.service;

import br.com.bills.dto.CreateBillsDTO;
import br.com.bills.exception.NotFoundException;
import br.com.bills.model.Bills;
import br.com.bills.repository.BillsRepository;
import br.com.bills.service.rate.CalculatorRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillsService {

    @Autowired
    private BillsRepository billsRepository;

    public Bills save(CreateBillsDTO createBillsDTO) {

        final int daysOfDelay = Period.between(createBillsDTO.getInvoiceDate(), LocalDate.now()).getDays();
        createPaymentRate(createBillsDTO, daysOfDelay);
        final Bills bills = new Bills(createBillsDTO);

        return billsRepository.save(bills);
    }

    private void createPaymentRate(CreateBillsDTO createBillsDTO, int daysOfDelay) {
        if (daysOfDelay != 0) {
            createBillsDTO.setDaysOfDelay(daysOfDelay);
            final CalculatorRate calculatorRate = new CalculatorRate(createBillsDTO);
            final BigDecimal rate = createBillsDTO.getBillAmount().add(calculatorRate.getInterestValue());
            createBillsDTO.setCorrectedValue(rate);
        }
    }

    public List<Bills> findAll() {
        List<Bills> bills = new ArrayList<>();
        billsRepository.findAll().forEach(bills::add);
        return bills;
    }

    public Bills findById(Long id) {
        return billsRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
