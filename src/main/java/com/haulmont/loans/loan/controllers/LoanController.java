package com.haulmont.loans.loan.controllers;

import com.haulmont.loans.loan.entities.Loan;
import com.haulmont.loans.loan.entities.LoanDto;
import com.haulmont.loans.loan.services.LoanMapper;
import com.haulmont.loans.loan.services.LoanService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
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

    @GetMapping("/find")
    public List<LoanDto> findAll() {
        return loanService.findAll().stream()
                .map(loanMapper::loanToLoanDto).collect(Collectors.toList());
    }

    @PostMapping(path = {"/status"})
    public LoanDto updateStatus(@RequestBody @Valid LoanDto loanDto) {
        Loan loan = loanService.findById(loanDto.getId()).orElseThrow(EntityNotFoundException::new);
        loanMapper.updateLoanFromLoanDto(loanDto, loan);
        return loanMapper.loanToLoanDto(loanService.save(loan));
    }
}