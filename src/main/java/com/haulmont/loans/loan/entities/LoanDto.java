package com.haulmont.loans.loan.entities;

import com.haulmont.loans.client.entities.ClientDto;
import com.haulmont.loans.manager.entities.ManagerDto;

import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LoanDto implements Serializable {
    private final Long id;
    private final ClientDto client;
    private final ManagerDto manager;
    @Positive
    private final BigDecimal amount;
    private final LoanStatus loanStatus;

    public LoanDto(Long id, ClientDto client, ManagerDto manager, BigDecimal amount, LoanStatus loanStatus) {
        this.id = id;
        this.client = client;
        this.manager = manager;
        this.amount = amount;
        this.loanStatus = loanStatus;
    }

    public Long getId() {
        return id;
    }

    public ClientDto getClient() {
        return client;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto entity = (LoanDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.client, entity.client) &&
                Objects.equals(this.manager, entity.manager) &&
                Objects.equals(this.amount, entity.amount) &&
                Objects.equals(this.loanStatus, entity.loanStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, manager, amount, loanStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "client = " + client + ", " +
                "manager = " + manager + ", " +
                "amount = " + amount + ", " +
                "loanStatus = " + loanStatus + ")";
    }
}