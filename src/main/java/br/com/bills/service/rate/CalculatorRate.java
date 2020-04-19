package br.com.bills.service.rate;

import br.com.bills.dto.CreateBillsDTO;

import java.math.BigDecimal;

public class CalculatorRate {
    private static final int TREE_DAY = 3;
    private static final int FIVE_DAY = 5;
    private final CreateBillsDTO createBillsDTO;

    public CalculatorRate(CreateBillsDTO createBillsDTO) {
        this.createBillsDTO = createBillsDTO;
    }

    public BigDecimal getInterestValue() {
        if (this.createBillsDTO.getDaysOfDelay() <= TREE_DAY) {
            return new UpToThreeDays(this.createBillsDTO).calculate();
        }
        if (this.createBillsDTO.getDaysOfDelay() > TREE_DAY && createBillsDTO.getDaysOfDelay() < FIVE_DAY) {
            return new MoreThreeDays(this.createBillsDTO).calculate();
        }
        if (this.createBillsDTO.getDaysOfDelay() > FIVE_DAY) {
            return new MoreFiveDays(this.createBillsDTO).calculate();
        }
        return new BigDecimal(0.0);
    }

}
