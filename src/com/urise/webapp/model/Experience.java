package com.urise.webapp.model;

import java.util.Objects;
import java.time.LocalDate;

public class Experience {
    private LocalDate beginDate;
    private LocalDate endDate;
    private String function;
    private String specification;

    public Experience(LocalDate beginDate, LocalDate endDate, String function, String specification) {
        Objects.requireNonNull(beginDate, "beginDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(function, "function must not be null");
        this.function = function;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.specification = specification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return beginDate.equals(that.beginDate) && endDate.equals(that.endDate) && function.equals(that.function) && specification.equals(that.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginDate, endDate, function, specification);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", function='" + function + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }

}