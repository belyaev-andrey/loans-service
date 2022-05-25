package com.haulmont.loans.loan.services;

import com.haulmont.loans.loan.entities.Loan;
import com.haulmont.loans.loan.repositories.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional(readOnly = true)
    public  List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Transactional
    public <S extends Loan> S save(S entity) {
        return loanRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public  Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }
}