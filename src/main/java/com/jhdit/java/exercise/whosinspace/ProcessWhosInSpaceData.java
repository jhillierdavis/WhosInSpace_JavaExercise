package com.jhdit.java.exercise.whosinspace;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;

public class ProcessWhosInSpaceData {
    public static final String SPACECRAFT_NAME = "craft";
    public static final String ASTRONAUT_NAME = "name";
    private Set<Spacecraft> spacecraftSet = new HashSet<>();

    public ProcessWhosInSpaceData(InputStream inputStream) throws Exception {
        if (null == inputStream ) { throw new IllegalArgumentException("NULL inputStream"); }

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = new ObjectMapper().readValue(inputStream, Map.class);
        this.processJsonInputData(map);

    }

    public Set<Spacecraft> getSpacecraft()   {
        return spacecraftSet;
    }

    private void processJsonInputData(Map<String,Object> map)  {
        ArrayList<Map<String, String>> craftPeople = (ArrayList<Map<String, String>>) map.get("people");

        for (Map<String, String> entry: craftPeople )	{
            String spacecraftName = entry.get(SPACECRAFT_NAME);
            String astronautName = entry.get(ASTRONAUT_NAME);
            Spacecraft spacecraft = this.findOrCreateSpacecraft(spacecraftName);
            spacecraft.addCrewMember(new Astronaut(astronautName));
        }

    }

    private Spacecraft findOrCreateSpacecraft(final String name) {
        for(Spacecraft spacecraft: this.spacecraftSet)   {
            if (spacecraft.getName().equals(name.trim())) {
                return spacecraft;
            }
        }

        Spacecraft spacecraft = new Spacecraft(name);
        this.spacecraftSet.add(spacecraft);
        return spacecraft;
    }
}
