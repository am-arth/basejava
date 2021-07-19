package com.urise.webapp.model;

import java.io.Serializable;
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
}