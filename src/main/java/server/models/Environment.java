package server.models;

public class Environment
{
    private float humidity;
    private float temperature;
    private float lumen;

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
}

