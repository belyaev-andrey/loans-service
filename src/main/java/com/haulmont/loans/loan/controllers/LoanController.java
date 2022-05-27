package com.haulmont.loans.loan.controllers;

import com.haulmont.loans.loan.entities.Loan;
import com.haulmont.loans.loan.entities.LoanDto;
import com.haulmont.loans.loan.services.LoanMapper;
import com.haulmont.loans.loan.services.LoanService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/loans")
class LoanController {
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    public LoanController(LoanService loanService,
                          LoanMapper loanMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @GetMapping("/all")
    public List<LoanDto> findAll() {
        return loanService.findAll().stream()
                .map(loanMapper::loanToLoanDto).collect(Collectors.toList());
    }

    @PostMapping(path = {"/{id}"})
    public LoanDto updateLoan(@PathVariable Long id, @RequestBody @Valid LoanDto loanDto) {
        Loan loan = loanService.findById(id).orElseThrow(EntityNotFoundException::new);
        loanMapper.updateLoanFromLoanDto(loanDto, loan);
        return loanMapper.loanToLoanDto(loanService.save(loan));
    }

    @GetMapping("/{id}")
    public LoanDto findById(@PathVariable Long id) {
        return loanMapper.loanToLoanDto(loanService.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }
}