package br.com.bills.controller;

import br.com.bills.model.Bills;
import br.com.bills.service.BillsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BillsController.class)
public class BillsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillsService billsService;

    @Test
    public void testCreateControllerSucess() throws Exception {

        when(billsService.save(any())).thenReturn(new Bills());

        this.mockMvc.perform(post("/api/v1/bills")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"teste\", \"billAmount\":\"100\",\"invoiceDate\":\"2020-04-18\"," +
                        "\"paymentDate\":\"2020-04-18\"}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testFindAllControllerSucess() throws Exception {
        List<Bills> bills = new ArrayList<>();
        when(billsService.findAll()).thenReturn(bills);

        this.mockMvc.perform(get("/api/v1/bills/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testfindByIdControllerSucess() throws Exception {
        when(billsService.findById(1L)).thenReturn(new Bills());

        this.mockMvc.perform(get("/api/v1/bills/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}