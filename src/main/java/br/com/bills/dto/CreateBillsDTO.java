package br.com.bills.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateBillsDTO {

    @NotEmpty(message = "bills.name.notEmpty")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "bills.billAmount.notEmpty")
    @JsonProperty("billAmount")
    private BigDecimal billAmount;

    @NotNull(message = "bills.invoiceDate.notEmpty")
    @JsonProperty("invoiceDate")
    private LocalDate invoiceDate;

    @NotNull(message = "bills.paymentDate.notEmpty")
    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @JsonProperty("daysOfDelay")
    private Integer daysOfDelay;

    @JsonProperty("correctedValue")
    private BigDecimal correctedValue;

}
