package server.models;

import server.repositories.ClimateProperty;

import java.util.HashMap;

public class Climate
{
    private float temperature;
    private float humidity;
    private float lumen;

    public Climate(float temperature, float humidity, float lumen)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.lumen = lumen;
    }

    public float getHumidity()
    {
        return humidity;
    }

    public void setHumidity(float humidity)
    {
        this.humidity = humidity;
    }

    public float getTemperature()
    {
        return temperature;
    }

    public void setTemperature(float temperature)
    {
        this.temperature = temperature;
    }

    public float getLumen()
    {
        return lumen;
    }

    public void setLumen(float lumen)
    {
        this.lumen = lumen;
    }

    public float getProperty(String property)
    {
        if (property == ClimateProperty.Temperature)
            return temperature;
        else if (property == ClimateProperty.Humidity)
            return humidity;
        else if (property == ClimateProperty.Lumen)
            return lumen;
        else
            throw new IllegalArgumentException();
    }
}

