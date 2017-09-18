package com.jhdit.java.exercise.whosinspace;

/**
 * Represents an space craft crew member.
 */

public class Astronaut {
    private String name;


    public Astronaut(final String name)   {
        if (null == name ) { throw new IllegalArgumentException("Invalid name: " + name); }
        this.name = name;
    }


    public String getName()    {
        return this.name;
    }

    public String getSurname()  {
        String[] array = this.name.split( "\\b" );
        return array[ array.length - 1];
    }

    @Override
    public String toString()    {
        return this.name;
    }

}
