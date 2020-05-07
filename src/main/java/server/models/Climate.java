package server.models;

import server.repositories.ClimateProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Climate
{
    public final static String TEMPERATURE = "temperature";
    public final static String HUMIDITY = "humidity";
    public final static String LUMEN = "lumen";

    private Map<String, Float> properties = new HashMap<>();

    public Climate()
    {
        properties.put(TEMPERATURE, 0f);
        properties.put(HUMIDITY, 0f);
        properties.put(LUMEN, 0f);
    }

    public float getProperty(String property)
    {
        if (!properties.containsKey(property))
            throw new IllegalArgumentException();
        return properties.get(property);
    }

    public float setProperty(String property, float value)
    {
        if (!properties.containsKey(property))
            throw new IllegalArgumentException();
        return properties.put(property, value);
    }

    public Set<Map.Entry<String, Float>> getProperties()
    {
        return properties.entrySet();
    }
}

