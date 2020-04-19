package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoreFiveDaysTest {

    @Test
    void testMoreFiveDaysSucess() {

        CreateBillsDTO createBillsDTO = new CreateBillsDTO();
        createBillsDTO.setDaysOfDelay(6);
        createBillsDTO.setBillAmount(new BigDecimal(2500));

        ProcessRate processRate = new MoreFiveDays(createBillsDTO);
        BigDecimal tax = processRate.calculate();

        assertEquals(new BigDecimal("126.50").setScale(2), tax);
    }
}