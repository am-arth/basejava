package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private Link homePage;
    private List<Experience> experiences = new ArrayList<>();

    public Organization(String name, String url,  Experience... experiences) {
        this(new Link(name, url), Arrays.asList(experiences));
    }

    public Organization(Link homePage, List<Experience> experiences) {
        this.homePage = homePage;
        this.experiences = experiences;
    }

    public Link getLink() {
        return homePage;
    }

    public List<Experience> getExperience() {
        return experiences;
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
                ", experiences=" + experiences +
                '}';
    }

    public static class Experience implements Serializable{
        private final LocalDate beginDate;
        private final LocalDate endDate;
        private final String function;
        private final String specification;

        public Experience(String function, LocalDate beginDate, LocalDate endDate, String specification) {
            Objects.requireNonNull(beginDate, "beginDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(function, "function must not be null");
            this.beginDate = beginDate;
            this.endDate = endDate;
            this.function = function;
            this.specification = specification;
        }

        public LocalDate getBeginDate() {
            return beginDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getFunction() {
            return function;
        }

        public String getSpecification() {
            return specification;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experience that = (Experience) o;
            return Objects.equals(beginDate, that.beginDate) && Objects.equals(endDate, that.endDate) && Objects.equals(function, that.function) && Objects.equals(specification, that.specification);
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

}