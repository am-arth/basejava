package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final String name;
    private final String specification;
    private final String function;

    public Organization(String name, String url, String function, LocalDate beginDate, LocalDate endDate, String specification) {
        Objects.requireNonNull(beginDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(name, "title must not be null");
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.name = name;
        this.homePage = new Link(name, url);
        this.specification = specification;
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return beginDate.equals(that.beginDate) && endDate.equals(that.endDate) && name.equals(that.name) && homePage.equals(that.homePage) && specification.equals(that.specification) && function.equals(that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginDate, endDate, name, homePage, specification, function);
    }

    @Override
    public String toString() {
        return "Organization{" +
                ", homePage=" + homePage +
                ", function=" + function +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}