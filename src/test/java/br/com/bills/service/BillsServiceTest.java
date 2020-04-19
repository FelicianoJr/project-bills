package br.com.bills.service;

import br.com.bills.dto.CreateBillsDTO;
import br.com.bills.model.Bills;
import br.com.bills.repository.BillsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillsServiceTest {

    @Mock
    private BillsRepository billsRepository;

    @InjectMocks
    private BillsService billsService;
    private CreateBillsDTO createBillsDTO;

    @Before
    public void setup() {
        this.createBillsDTO = new CreateBillsDTO();
        this.createBillsDTO.setBillAmount(new BigDecimal(100.0));
        this.createBillsDTO.setInvoiceDate(LocalDate.now());
        this.createBillsDTO.setPaymentDate(LocalDate.now());
        this.createBillsDTO.setName("teste");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void createBillsSucess() {

        billsService.save(this.createBillsDTO);

        ArgumentCaptor<Bills> argumentCaptor = ArgumentCaptor.forClass(Bills.class);

        Mockito.verify(billsRepository).save(argumentCaptor.capture());

        assertEquals(100, argumentCaptor.getValue().getBillAmount().intValue());
        assertEquals("teste", argumentCaptor.getValue().getName());

    }

    @Test
    public void findAll() {
        List<Bills> billsList = new ArrayList<>();
        billsList.add(new Bills());
        when(billsRepository.findAll()).thenReturn(billsList);

        final List<Bills> result = billsService.findAll();

        assertEquals(1, result.size());
        verify(billsRepository).findAll();
    }

    @Test
    public void findById() {
        final Bills bills = new Bills(createBillsDTO);
        Optional optionalBills = java.util.Optional.ofNullable(bills);
        when(billsRepository.findById(1L)).thenReturn(optionalBills);

        Bills result = billsService.findById(1L);

        assertEquals("teste", result.getName());
        verify(billsRepository).findById(anyLong());
    }
}