package com.haulmont.loans.loan.services;

import com.haulmont.loans.loan.entities.Loan;
import com.haulmont.loans.loan.repositories.LoanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

    private String getAuthUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken token) {
            return (String)token.getToken().getClaims().get("preferred_username");
        } else {
            return "";
        }
    }

    @Transactional(readOnly = true)
    public  List<Loan> findAll() {
        String name = getAuthUsername();
        return loanRepository.findLoanByManager(name);
    }

    @Transactional
    public <S extends Loan> S save(S entity) {
        return loanRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public  Optional<Loan> findById(Long id) {
        String name = getAuthUsername();
        return loanRepository.findByIdAndManager(id, name);
    }

}