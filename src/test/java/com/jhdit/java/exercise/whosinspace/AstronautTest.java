package com.jhdit.java.exercise.whosinspace;


import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Uses JUnitParams see: https://github.com/Pragmatists/junitparams/wiki/Quickstart
 */

@RunWith(JUnitParamsRunner.class)
public class AstronautTest {

    @Test
    @Parameters({
            ",",
            "John Smith, Smith",
            "Mary Sue Van Pelt, Pelt",
            "Buzz, Buzz"
            })
    public void getSurname(final String inputFullname, final String expectedSurname)    {
        // Given
        Astronaut astronaut = new Astronaut(inputFullname);

        // When
        String surname = astronaut.getSurname();

        // Then
        assertThat(surname, is(expectedSurname));
    }
}
