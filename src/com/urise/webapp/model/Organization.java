package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class Organization {
    private Link homePage;
    private List<Experience> experiences = new ArrayList<>();

    public Organization(String name, String url, String function, LocalDate beginDate, LocalDate endDate, String specification) {
        Objects.requireNonNull(name, "Name must not be null");
        this.homePage = new Link(name, url);
        addPeriod(beginDate, endDate, function);
    }

    public void addPeriod(LocalDate beginDate, LocalDate endDate, String function) {
        this.experiences.add(new Experience(beginDate, endDate, function));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && experiences.equals(that.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, experiences);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", periods=" + experiences +
                '}';
    }
}