package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UpToThreeDaysTest {

    @Test
    void testUpToThreeDaysSucess() {
        CreateBillsDTO createBillsDTO = new CreateBillsDTO();
        createBillsDTO.setDaysOfDelay(3);
        createBillsDTO.setBillAmount(new BigDecimal(500));

        ProcessInterest processInterest = new UpToThreeDays(createBillsDTO);
        BigDecimal tax = processInterest.calculate();

        assertEquals(new BigDecimal("10.06").stripTrailingZeros(), tax);
    }
}