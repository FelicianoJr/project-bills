package br.com.bills.controller;

import br.com.bills.dto.BillsDTO;
import br.com.bills.dto.CreateBillsDTO;
import br.com.bills.model.Bills;
import br.com.bills.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/bills")
public class BillsController {

    @Autowired
    private BillsService billsService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateBillsDTO createBillsDTO, UriComponentsBuilder componentsBuilder) {
        final Bills bills = billsService.save(createBillsDTO);
        UriComponents uriComponents = componentsBuilder.path("/api/v1/bills/{id}").buildAndExpand(bills.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/all")
    public List<BillsDTO> findAll() {
        return billsService.findAll().stream().map(BillsDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BillsDTO findById(@PathVariable Long id) {
        Bills bills = billsService.findById(id);
        return Stream.of(bills)
                .map(BillsDTO::new)
                .findFirst()
                .orElse(new BillsDTO());

    }

}
    
    

