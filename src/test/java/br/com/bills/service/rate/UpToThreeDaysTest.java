package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpToThreeDaysTest {

    @Test
    void testUpToThreeDaysSucess() {
        CreateBillsDTO createBillsDTO = new CreateBillsDTO();
        createBillsDTO.setDaysOfDelay(3);
        createBillsDTO.setBillAmount(new BigDecimal(500));

        ProcessRate processRate = new UpToThreeDays(createBillsDTO);
        BigDecimal tax = processRate.calculate();

        assertEquals(new BigDecimal("10.06").stripTrailingZeros(), tax);
    }
}