package com.jhdit.java.exercise.whosinspace;

import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Output details of who's currently in space (crew member by spacecraft)
 */

public class DisplayWhosInSpace {
    public static final String ASTROS_JSON = "http://api.open-notify.org/astros.json";

    public static void main(String[] args) throws Exception {
        Set<Spacecraft> spacecraftSet  = getLiveData();
        // formatAndDisplay(spacecraftSet);
        display(spacecraftSet);
    }

    private static Set<Spacecraft> getLiveData() throws Exception {
        URLConnection conn = new URL(ASTROS_JSON) .openConnection();
        ProcessWhosInSpaceData process =  new ProcessWhosInSpaceData(conn.getInputStream());
        return process.getSpacecraft();
    }
/*
    private static void formatAndDisplay(Set<Spacecraft> spacecraftSet) {
        System.out.println( "Name                | Craft"  );
        System.out.println( "--------------------|------"  );

        for(Spacecraft spacecraft: spacecraftSet )    {
            for(Astronaut astronaut: spacecraft.getCrew())  {
                System.out.println( String.format("%-20s", astronaut.getName() ) + " | " + String.format("%-10s",  spacecraft.getName() ) );
            }
        }
    }
*/
    private static int getLongestEntryLength(final List<String> list)  {
        final int maxLength = list.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        return maxLength;
    }

    private static void display(final Set<Spacecraft> spacecraftSet)   {
        List<String> columnCrew = spacecraftSet.stream().
                map(s -> s.getCrew()).
                flatMap(l -> l.stream()).
                map(a -> a.getName()).
                collect(Collectors.toList()); // Collect all Astronaut names
        List<String> columnCraft = spacecraftSet.stream().map(e -> e.getName()).collect( Collectors.toList() );

        /*
        for(Spacecraft spacecraft: spacecraftSet )    {
            for(Astronaut astronaut: spacecraft.getCrew())  {
                columnCrew.add(astronaut.getName());
            }
        }
*/
        int maxCrew = getLongestEntryLength(columnCrew);
        int maxCraft = getLongestEntryLength(columnCraft);

        System.out.println( formatColumn("Name", maxCrew ) + " | " + formatColumn("Craft", maxCraft) );
        System.out.println( formatColumn("-", maxCrew).replace(" ", "-") + " | " + formatColumn("-", maxCraft).replace(" ", "-"));

        for(Spacecraft spacecraft: spacecraftSet )    {
            /*
            for(Astronaut astronaut: spacecraft.getCrew())  {
                System.out.print(formatColumn(astronaut.getName(), maxCrew ) + " | ");
                System.out.println(formatColumn(spacecraft.getName(), maxCraft ) );
            }
            */

            /*
            List<Astronaut> sortedCrew = spacecraft.getCrew().
                    stream().sorted( (a1, a2) -> a1.getSurname().
                    compareTo(a2.getSurname())).
                    collect(Collectors.toList());
            */

            /*
            List<Astronaut> sortedCrew = spacecraft.getCrew().
                    stream().sorted( Comparator.comparing(Astronaut::getSurname)).
                    collect(Collectors.toList());


            for(Astronaut astronaut: sortedCrew)  {
                System.out.print(formatColumn(astronaut.getName(), maxCrew ) + " | ");
                System.out.println(formatColumn(spacecraft.getName(), maxCraft ) );
            }
            */

            spacecraft.getCrew().
                    stream().sorted( Comparator.comparing(Astronaut::getSurname)).
                    collect(Collectors.toList()).
                    forEach(a -> {
                                System.out.print(formatColumn(a.getName(), maxCrew) + " | " );
                                System.out.println(formatColumn(spacecraft.getName(), maxCraft));
                            }
                    );



        }
    }

    private static String formatColumn(String value, int maxLength)  {
        return String.format("%-" + maxLength + "s", value);
    }

}
