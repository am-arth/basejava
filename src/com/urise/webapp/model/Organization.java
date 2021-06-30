package com.urise.webapp.model;

import java.time.LocalDate;

public class Organization {

    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final String name;
    private final String homePage;
    private final String specification;

    public Organization(String name, LocalDate beginDate, LocalDate endDate, String homepage, String specification) {

        this.beginDate = beginDate;
        this.endDate = endDate;
        this.name = name;
        this.homePage = homepage;
        this.specification = specification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!beginDate.equals(that.beginDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!name.equals(that.name)) return false;
        if (!homePage.equals(that.homePage)) return false;
        return specification.equals(that.specification);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}