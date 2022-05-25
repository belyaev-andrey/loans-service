package com.haulmont.loans.loan.repositories;

import com.haulmont.loans.loan.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("select l from Loan l where upper(l.manager.username) = upper(:username)")
    List<Loan> findLoanByManager(@Param("username") String username);

    @Query("select l from Loan l where l.id = :id and upper(l.manager.username) = upper(:name)")
    Optional<Loan> findByIdAndManager(@Param("id") Long id, @Param("name") String name);

}