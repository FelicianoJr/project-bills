package br.com.bills.dto;

import br.com.bills.model.Bills;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BillsDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("billAmount")
    private BigDecimal billAmount;

    @JsonProperty("invoiceDate")
    private LocalDate invoiceDate;

    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @JsonProperty("daysOfDelay")
    private Integer daysOfDelay;

    @JsonProperty("correctedValue")
    private BigDecimal correctedValue;

    public BillsDTO(Bills bills) {
        this.name = bills.getName();
        this.billAmount = bills.getBillAmount();
        this.invoiceDate = bills.getInvoiceDate();
        this.paymentDate = bills.getPaymentDate();
        this.daysOfDelay = bills.getDaysOfDelay();
        this.correctedValue = bills.getCorrectedValue();
    }
}
