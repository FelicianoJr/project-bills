package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoreFiveDaysTest {

    @Test
    void testMoreFiveDaysSucess() {

        CreateBillsDTO createBillsDTO = new CreateBillsDTO();
        createBillsDTO.setDaysOfDelay(6);
        createBillsDTO.setBillAmount(new BigDecimal(2500));

        ProcessInterest processInterest = new MoreFiveDays(createBillsDTO);
        BigDecimal tax = processInterest.calculate();

        assertEquals(new BigDecimal("126.50").setScale(2), tax);
    }
}