package com.jhdit.java.exercise.whosinspace;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.*;

import java.io.InputStream;
import java.util.Set;

/**
 * Unit test for ProcessWhosInSpaceData
 */

public class ProcessWhosInSpaceDataTest {
    private static final String TEST_JSON_FILE = "astros_20151209.json";


    @Test
    public void processWhosInSpaceData() throws Exception   {
        // Given
        InputStream jsonInput = getClass().getResourceAsStream(TEST_JSON_FILE);
        assertThat(jsonInput, notNullValue(InputStream.class));
        ProcessWhosInSpaceData processedData = new ProcessWhosInSpaceData(jsonInput);

        // When
        Set<Spacecraft> spacecraftList = processedData.getSpacecraft();

        // Then
        assertMatchesTestData(spacecraftList);
    }

    private void assertMatchesTestData(Set<Spacecraft> spacecraftSet) {
        assertThat(spacecraftSet.isEmpty(), is(Boolean.FALSE));
        assertThat(spacecraftSet.size(), is(1));

        for (Spacecraft spacecraft: spacecraftSet) {
            assertThat(spacecraft.getName(), is("ISS"));
            assertThat(spacecraft.getCrew(), notNullValue(Set.class));
            assertThat(spacecraft.getCrew().size(), is(6));
        }
    }

}
