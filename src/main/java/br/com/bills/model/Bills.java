package br.com.bills.model;

import br.com.bills.dto.CreateBillsDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GenerationType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor
@Table(name = "bills")
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bill_amount")
    private BigDecimal billAmount;

    @Column(name = "corrected_value")
    private BigDecimal correctedValue;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "days_of_delay")
    private Integer daysOfDelay;


    public Bills(CreateBillsDTO createBillsDTO){
        this.name = createBillsDTO.getName();
        this.billAmount = createBillsDTO.getBillAmount();
        this.invoiceDate = createBillsDTO.getInvoiceDate();
        this.paymentDate = createBillsDTO.getPaymentDate();
        this.correctedValue = createBillsDTO.getCorrectedValue();
        this.daysOfDelay = createBillsDTO.getDaysOfDelay();
    }


}
