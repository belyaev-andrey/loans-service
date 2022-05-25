package com.haulmont.loans.client.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Objects;

public class ClientDto implements Serializable {
    private final Long id;
    private final String name;
    @Max(10)
    @Min(0)
    private final Integer rating;

    public ClientDto(Long id, String name, Integer rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto entity = (ClientDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.rating, entity.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "rating = " + rating + ")";
    }
}