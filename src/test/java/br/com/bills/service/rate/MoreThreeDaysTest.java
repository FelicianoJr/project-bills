package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoreThreeDaysTest {

    @Test
    void testMoreThreeDaysSucess() {

        CreateBillsDTO createBillsDTO = new CreateBillsDTO();
        createBillsDTO.setDaysOfDelay(4);
        createBillsDTO.setBillAmount(new BigDecimal(1500));

        ProcessRate processRate = new MoreThreeDays(createBillsDTO);
        BigDecimal tax = processRate.calculate();

        assertEquals(new BigDecimal("45.40").setScale(2), tax);
    }
}