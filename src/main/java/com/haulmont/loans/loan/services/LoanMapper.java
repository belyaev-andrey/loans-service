package com.haulmont.loans.loan.services;

import com.haulmont.loans.loan.entities.Loan;
import com.haulmont.loans.loan.entities.LoanDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LoanMapper {
    Loan loanDtoToLoan(LoanDto loanDto);

    LoanDto loanToLoanDto(Loan loan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLoanFromLoanDto(LoanDto loanDto, @MappingTarget Loan loan);
}