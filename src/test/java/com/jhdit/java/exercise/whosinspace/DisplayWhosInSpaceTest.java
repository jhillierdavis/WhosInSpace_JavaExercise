package com.jhdit.java.exercise.whosinspace;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DisplayWhosInSpaceTest {
    private static Set<Spacecraft> testSpacecraftSet = new HashSet<>();
    private static String LONGEST_SPACECRAFT_NAME = "Millennium Falcon";
    private static String LONGEST_ASTRONAUT_NAME = "Princess Leia Organa";


    @BeforeClass
    public static void oneTimeSetUpOfTestData() {
        // From https://en.wikipedia.org/wiki/List_of_Star_Wars_spacecraft

        Spacecraft millenniumFalcon = new Spacecraft(LONGEST_SPACECRAFT_NAME);
        millenniumFalcon.addCrewMember(new Astronaut("Hans Solo"));
        millenniumFalcon.addCrewMember(new Astronaut("Chewbacca"));

        testSpacecraftSet.add(millenniumFalcon);

        Spacecraft tantiveIV = new Spacecraft("Tantive IV");
        tantiveIV.addCrewMember(new Astronaut("Captain Antilles"));
        tantiveIV.addCrewMember(new Astronaut(LONGEST_ASTRONAUT_NAME));
        tantiveIV.addCrewMember(new Astronaut("C-3P0"));
        tantiveIV.addCrewMember(new Astronaut("R2-D2"));

        testSpacecraftSet.add(tantiveIV);

        Spacecraft slave1 = new Spacecraft("Slave 1");
        slave1.addCrewMember(new Astronaut("Boba Fett"));

        testSpacecraftSet.add(slave1);

        Spacecraft xwingFighter = new Spacecraft("X-wing fighter");
        xwingFighter.addCrewMember(new Astronaut("Luke Skywalker"));

        testSpacecraftSet.add(xwingFighter);

        // System.out.println(testSpacecraftSet);
    }

    @Test
    public void findLongestCrewMemberNameLength() {
        // When
        assertThat(testSpacecraftSet.size(), is(4));
        Optional<Spacecraft> optionalSpacecraft = testSpacecraftSet.stream().findFirst();

        // Then
        assertThat(optionalSpacecraft.isPresent(), is(Boolean.TRUE));
        assertThat(optionalSpacecraft.get().getName(), is("Tantive IV"));

        // When
        Spacecraft spacecraft = optionalSpacecraft.get();
        System.out.println(spacecraft);
        Optional<Astronaut> max = spacecraft.getCrew().stream().
                max((a1, a2) -> a1.getName().length() - a2.getName().length());
        int maxNameLength = max.isPresent() ? max.get().getName().length() : 0;
        assertThat(maxNameLength, is(LONGEST_ASTRONAUT_NAME.length()));
        // max.ifPresent(System.out::println);
    }

    @Test
    public void findLongestSpacecraftNameLength()   {
        Optional<Spacecraft> optional = testSpacecraftSet.stream().max((s1, s2) -> s1.getName().length() - s2.getName().length());

        int maxNameLength = optional.isPresent() ? optional.get().getName().length() : 0;
        assertThat(maxNameLength, is(LONGEST_SPACECRAFT_NAME.length()));
    }

    @Test
    public void findLongestEntryName()   {
        // Given
        Set<String> set = new LinkedHashSet<String>(); // Used to maintain insertion order
        set.add("Alpha");
        set.add("Beta");
        set.add("Gamma");
        set.add("Dela");
        set.add("Epsilon");
        set.add("Zeta");

        // When
        final int maxLength = set.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // Optional<String> longestEntry = set.stream().max((e1, e2) -> e1.length()  - e2.length() );
        // int maxLength = ( longestEntry.isPresent() ? longestEntry.get() : "" ).length();

        // Then
        assertThat(maxLength , is("Epsilon".length()));
    }


}
