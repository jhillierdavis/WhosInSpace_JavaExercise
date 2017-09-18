package com.jhdit.java8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class FlatMapTest {
    static List<Developer> team = new ArrayList<>();
    static  Developer polyglot, busy;

    @BeforeClass
    public static void setupTestData()    {
        polyglot = new Developer("esoteric");
        polyglot.add("clojure");
        polyglot.add("scala");
        polyglot.add("java");
        polyglot.add("groovy");
        polyglot.add("go");

        busy = new Developer("pragmatic");
        busy.add("java");
        busy.add("javascript");

        team.add(polyglot);
        team.add(busy);
    }

    @Test
    public void findUnion() {
        // When: Collate all programming languages
        Set<String> teamLanguages = team.stream().
                map(d -> d.getLanguages()).
                flatMap(l -> l.stream()). // Flattens each generated stream into a single stream
                collect(Collectors.toSet());

        // Then:
        assertThat(teamLanguages.size(), is(6) );
        assertTrue(teamLanguages.containsAll(polyglot.getLanguages()));
        assertTrue(teamLanguages.containsAll(busy.getLanguages()));
    }


    @Test
    public void findIntersection() {
        // When: Collate all programming languages
        Set<String> sharedLanguages = polyglot.getLanguages().stream().
                filter(busy.getLanguages()::contains ).
                collect(Collectors.toSet());

        // Then:
        assertThat( sharedLanguages.size(), is(1) );
        assertTrue( sharedLanguages.contains("java") );
    }


}

class Developer {

    private String name;
    private Set<String> languages;

    public Developer(String name) {
        this.languages = new HashSet<>();
        this.name = name;
    }

    public void add(String language) {
        this.languages.add(language);
    }

    public Set<String> getLanguages() {
        return languages;
    }
}