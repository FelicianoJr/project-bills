package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoreThreeDays implements ProcessInterest {

    private static final double FINE = 0.03;
    private static final double INTEREST_PER_DAY = 0.002;
    private static final int DAYS_OF_THE_MONTH = 30;
    private final CreateBillsDTO createBillsDTO;

    public MoreThreeDays(CreateBillsDTO createBillsDTO) {
        this.createBillsDTO = createBillsDTO;
    }

    @Override
    public BigDecimal calculate() {
        BigDecimal totalFine = this.createBillsDTO.getBillAmount().multiply(new BigDecimal(FINE))
                .setScale(2, RoundingMode.HALF_UP);

        final BigDecimal totalInterest = this.createBillsDTO.getBillAmount().multiply(new BigDecimal(INTEREST_PER_DAY))
                .setScale(2, RoundingMode.HALF_UP);
        final BigDecimal totalInterestDay = totalInterest.divide(new BigDecimal(DAYS_OF_THE_MONTH), 2, RoundingMode.HALF_UP);
        final BigDecimal amountInterest = totalInterestDay.multiply(new BigDecimal(this.createBillsDTO.getDaysOfDelay()));
        return totalFine.add(amountInterest);
    }
}
