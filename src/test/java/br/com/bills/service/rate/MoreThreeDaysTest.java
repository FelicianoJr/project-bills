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

        ProcessInterest processInterest = new MoreThreeDays(createBillsDTO);
        BigDecimal tax = processInterest.calculate();

        assertEquals(new BigDecimal("45.40").setScale(2), tax);
    }
}